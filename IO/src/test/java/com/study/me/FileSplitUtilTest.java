package com.study.me;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author FanQie
 * @date 2019/11/10 15:45
 */
public class FileSplitUtilTest {

    private static final String fileName = "test";

    @Test
    public void spiltTest() throws IOException {
        final int blocks = 10;
        FileSplitUtil.split(new File(fileName), blocks);
        for (int i = 0; i < blocks; ++i) {
            Assert.assertTrue(new File(fileName + "_" + i).exists());
        }
    }

    @Test
    public void mergeTest() throws IOException {
        File file = new File(fileName);
        file.createNewFile();

        FileSplitUtil.merge("merged_file",
                "test_0",
                "test_1",
                "test_2",
                "test_3",
                "test_4",
                "test_5",
                "test_6",
                "test_7",
                "test_8",
                "test_9");
        Assert.assertTrue(new File("merged_file").exists());
    }
}
