package com.study.me;

import java.io.IOException;

/**
 * @author fanqie
 * @date 2020/4/27
 */
public class ProcessDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        final Runtime runtime = Runtime.getRuntime();
        final String cmd = "E:\\demo.exe path";

        final Process process = runtime.exec(cmd);
        process.waitFor();

        System.out.println();
        process.destroy();

    }
}
