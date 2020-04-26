package com.study.socketdemo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author fanqie
 * @date 2020/4/26
 */
public class SocketDemoClient {

    public static void main(String[] args) {
        try (final Socket s = new Socket(InetAddress.getByName("localhost"), 9876);
             final DataOutputStream writer = StreamUtil.getOutputStream(s);
             final DataInputStream reader = StreamUtil.getInputStream(s);
             final BufferedReader stdReader = StreamUtil.getBufferedStdIn()) {

            final int maxLen = 1024;
            final byte[] buffer = new byte[maxLen];
            String line;
            while ((line = stdReader.readLine()) != null) {
                System.out.printf("client send: %s\n", line);

                writer.write(line.getBytes());
                int ansLen = reader.read(buffer, 0, maxLen);

                System.out.printf("server answer: %s\n", new String(buffer, 0, ansLen));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
