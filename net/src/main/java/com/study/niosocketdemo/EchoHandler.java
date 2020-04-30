package com.study.niosocketdemo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author fanqie
 * @date 2020/4/30
 */
public class EchoHandler implements Runnable {

    private static final int BUFFER_SIZE = 4096;

    private final SocketChannel socketChannel;
    private final SelectionKey key;
    private State state;
    final ByteBuffer buffer;

    public EchoHandler(final Selector selector, final SocketChannel socketChannel)
            throws IOException {
        this.socketChannel = socketChannel;
        this.state = State.WAIT_READ;
        buffer = ByteBuffer.allocate(BUFFER_SIZE);
        socketChannel.configureBlocking(false);
        this.key = socketChannel.register(selector, SelectionKey.OP_READ);
        key.attach(this);
    }

    @Override
    public void run() {
        switch (state) {
            case WAIT_READ:
                handleRead();
                break;
            case WAIT_WRITE:
                handleWrite();
                break;
            default:

        }
    }

    private void handleRead() {
        try {
            int len;
            while ((len = socketChannel.read(buffer)) != -1) {
                System.out.printf("%s\n", new String(buffer.array(), 0, len));
            }
            buffer.flip();
            state = State.WAIT_WRITE;
            key.interestOps(SelectionKey.OP_WRITE);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private void handleWrite() {
        try {
            socketChannel.write(buffer);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        state = State.WAIT_READ;
        key.interestOps(SelectionKey.OP_READ);
        buffer.clear();
    }

    private enum State {
        /*wait client */
        WAIT_READ,
        /*wait response */
        WAIT_WRITE,
        ;
    }
}
