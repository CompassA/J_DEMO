package com.study.socketdemo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author fanqie
 * @date 2020/4/26
 */
public class SocketDemoServer {

    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(9876, 10);
        final ThreadPoolExecutor pool = new ThreadPoolExecutor(
                5, 5, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20),
                new ThreadFactoryBuilder().setNameFormat("socket-worker%d").build());
        while (!Thread.currentThread().isInterrupted()) {
            final Socket clientSocket = serverSocket.accept();
            pool.submit(new ClientSocketHandler(clientSocket));
        }
        pool.shutdown();
    }

    private static class ClientSocketHandler implements Runnable {

        private final Socket clientSocket;

        public ClientSocketHandler(final Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (final DataInputStream reader = StreamUtil.getInputStream(clientSocket);
                 final DataOutputStream writer = StreamUtil.getOutputStream(clientSocket)) {

                final int maxLen = 1024;
                final byte[] buffer = new byte[1024];
                int len;
                while ((len = reader.read(buffer, 0, maxLen)) != -1) {
                    System.out.printf("server received: %s\n", new String(buffer, 0, len));
                    writer.write(buffer, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
