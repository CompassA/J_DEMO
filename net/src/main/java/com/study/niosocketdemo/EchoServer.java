package com.study.niosocketdemo;

import java.io.IOException;

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

    public void startServer() throws IOException {
        new Reactor(this.port).startReactor();
    }

    public int getPort() {
        return port;
    }

    public static void main(final String[] args) throws IOException {
        final EchoServer echoServer = new EchoServer(9876);
        echoServer.startServer();
    }
}
