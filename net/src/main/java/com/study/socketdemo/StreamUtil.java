package com.study.socketdemo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author fanqie
 * @date 2020/4/26
 */
public class StreamUtil {

    public static DataInputStream getInputStream(final Socket s) throws IOException {
        return new DataInputStream(s.getInputStream());
    }

    public static DataOutputStream getOutputStream(final Socket s) throws IOException {
        return new DataOutputStream(s.getOutputStream());
    }

    public static BufferedReader getBufferedStdIn() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
