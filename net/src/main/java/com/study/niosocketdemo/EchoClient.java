package com.study.niosocketdemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author fanqie
 * @date 2020/4/28
 */
public class EchoClient {

    private static final int BUFFER_CAPACITY = 4096;

    private final String ip;
    private final int port;

    public EchoClient(final String ip, final int port) {
        this.ip = ip;
        this.port = port;
    }

    public void startClient() {
        final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
        final InetSocketAddress inetSocketAddress = new InetSocketAddress(ip, port);

        try (final SocketChannel socketChannel = SocketChannel.open(inetSocketAddress)) {
            buffer.put("hello world".getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
            socketChannel.shutdownOutput();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) {
        new EchoClient("127.0.0.1", 9876).startClient();
    }
}
