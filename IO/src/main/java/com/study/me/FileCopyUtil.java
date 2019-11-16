package com.study.me;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author FanQie
 * @date 2019/11/16 17:34
 */
public final class FileCopyUtil {

    private static final int BUFFER_SIZE = 4096;

    public static void copy(final File src, final File dest) throws IOException {
        if (!dest.exists()) {
            if (!dest.createNewFile()) {
                return;
            }
        }
        try (final FileInputStream fis = new FileInputStream(src);
             final FileOutputStream fos = new FileOutputStream(dest);
             final FileChannel inChannel = fis.getChannel();
             final FileChannel outChannel = fos.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
            while (inChannel.read(byteBuffer)!= -1) {
                byteBuffer.flip();
                outChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        }
    }
}
