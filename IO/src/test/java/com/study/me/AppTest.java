package com.study.me;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Objects;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void test1() {
        printfDictionary(new File("F:"));
    }

    /**
     * 打印文件夹下所有文件
     * @param root 根目录
     */
    private static void printfDictionary(final File root) {
        if (root.isDirectory()) {
            File[] files = root.listFiles();
            if (Objects.isNull(files)) {
                return;
            }
            for (final File file : files) {
                printfDictionary(file);
            }
        } else {
            System.out.printf("%s\n", root.getAbsolutePath());
        }
    }

    /**
     * 创建文件并写入内容
     * @throws IOException IO异常
     */
    @Test
    public void test2() throws IOException {
        final File file = new File("text");
        if (!file.exists()) {
            Assert.assertTrue(file.createNewFile());
        }
        final OutputStream os = new BufferedOutputStream(new FileOutputStream(file, true));
        String output = "hello world!\r\n";
        os.write(output.getBytes());
        os.close();
    }

    @Test
    public void test3() throws IOException {
        final File file = new File("input_record.txt");
        if (!file.exists()) {
            Assert.assertTrue(file.createNewFile());
        }

        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        String line;

        while (!(line = reader.readLine()).equals("quits")) {
            String[] words = line.split(" ");
            for (final String word : words) {
                writer.write(word.toCharArray());
                writer.write('\n');
            }
        }

        writer.close();
        reader.close();
    }

    @Test
    public void test4() throws IOException {
        final File file = new File("input_record.txt");
        if (!file.exists()) {
            return;
        }
        final BufferedReader bf = new BufferedReader(new FileReader(file));

        String line;
        while ((line = bf.readLine()) != null) {
            System.out.printf("%s\n", line);
        }
    }
}