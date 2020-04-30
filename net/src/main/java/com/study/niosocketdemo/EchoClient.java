package com.study.niosocketdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author fanqie
 * @date 2020/4/28
 */
public class EchoClient {

    private static final int BUFFER_CAPACITY = 4096;

    private final BufferedReader reader;
    private final String ip;
    private final int port;

    public EchoClient(final String ip, final int port) {
        this.ip = ip;
        this.port = port;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void startClient() throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
        final InetSocketAddress inetSocketAddress = new InetSocketAddress(ip, port);

        String line;
        while ((line = reader.readLine()) != null) {
            try (final SocketChannel socketChannel = SocketChannel.open(inetSocketAddress)) {
                final byte[] bytes = line.getBytes();
                buffer.put(bytes, 0, Math.min(bytes.length, BUFFER_CAPACITY));
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
                socketChannel.shutdownOutput();

                final int len = socketChannel.read(buffer);
                System.out.printf("server echo: %s\n", new String(buffer.array(), 0, len));
                buffer.clear();
                socketChannel.shutdownInput();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(final String[] args) throws IOException {
        new EchoClient("127.0.0.1", 9876).startClient();
    }
}
