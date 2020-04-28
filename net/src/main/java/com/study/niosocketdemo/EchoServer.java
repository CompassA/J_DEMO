package com.study.niosocketdemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author fanqie
 * @date 2020/4/28
 */
public class EchoServer {

    private static final int BUFFER_CAPACITY = 4096;

    private final int port;

    public EchoServer(final int port) {
        this.port = port;
    }

    public void startServer() {
        try (final ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(port));

            final Selector selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (selector.select() > 0) {
                final Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    final SelectionKey key = keys.next();
                    if (key.isAcceptable()) {
                        final SocketChannel socketChannel = serverChannel.accept();
                        if (socketChannel != null) {
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        }
                    } else if (key.isReadable()) {
                        handle(key);
                    }
                    keys.remove();
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    private void handle(final SelectionKey key) {
        System.out.println("server has received msg!");
        final ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
        try (final SocketChannel channel = (SocketChannel) key.channel()) {
            while (channel.read(buffer) != -1) {
                buffer.flip();
                System.out.println(buffer);
                buffer.clear();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            key.cancel();
        }
    }

    public static void main(final String[] args) {
        final EchoServer echoServer = new EchoServer(9876);
        echoServer.startServer();
    }
}
