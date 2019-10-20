package com.study.me;

import org.junit.Test;

import java.io.File;
import java.util.Objects;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void test1() {
        printfDictionary(new File("E:"));
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
}
