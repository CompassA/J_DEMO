package com.study.me;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

    @Test
    public void copyTest() throws IOException {
        final File src = new File("src.jpg");
        final File dest = new File("dest.jpg");
        if (!dest.exists()) {
            boolean b = dest.createNewFile();
        }
        FileCopyUtil.copy(src, dest);
        Assert.assertEquals(src.length(),dest.length());
    }

    @Test
    public void splitNioTest() {
        final File src = new File("src_nio");
        FileSplitNioUtil.split(src, 1024 * 1024, ".mp4");
    }

    @Test
    public void mergeNioTest() {
        final ArrayList<File> list = new ArrayList<>(55);
        for (int i = 0; i < 55; ++i) {
            list.add(new File(FileSplitNioUtil.generateBlockFileName("src_nio", i, ".mp4")));
        }
        FileSplitNioUtil.merge(list, "merged.mp4");
    }
}
