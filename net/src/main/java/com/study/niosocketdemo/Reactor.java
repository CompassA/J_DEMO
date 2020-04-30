package com.study.niosocketdemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author fanqie
 * @date 2020/4/30
 */
public class Reactor {

    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    public Reactor(final int port) throws IOException {
        this.selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        final SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        key.attach(new AcceptHandler());
    }

    public void startReactor() throws IOException {
        while (!Thread.interrupted()) {
            selector.select();
            final Set<SelectionKey> keys = selector.selectedKeys();
            keys.forEach(this::dispatch);
            keys.clear();
        }
    }

    private void dispatch(final SelectionKey key) {
        final Runnable handler = (Runnable) key.attachment();
        if (handler != null) {
            handler.run();
        }
    }

    private class AcceptHandler implements Runnable {

        @Override
        public void run() {
            try {
                final SocketChannel socketChannel = serverSocketChannel.accept();
                new EchoHandler(selector, socketChannel);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }
}
