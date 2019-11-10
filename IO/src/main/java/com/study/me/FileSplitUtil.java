package com.study.me;

import java.io.*;

/**
 * @author FanQie
 * @date 2019/11/2 22:25
 */
public class FileSplitUtil {

    /**
     * @param file 被分割文件
     * @param blocks 分割成几块
     */
    public static void split(final File file, final int blocks)
            throws IOException {
        if (!file.exists()) {
            return;
        }

        //计算每个文件的大小，初始读流
        final int blockSize = (int) Math.ceil((double) file.length() / blocks);
        final InputStream is = new BufferedInputStream(new FileInputStream(file));

        //写入blocks个新文件
        for (int i = 0; i < blocks; ++i) {

            //缓冲区、文件名、写流
            final byte[] buffer = new byte[blockSize];
            final String fileName = String.format("%s_%d", file.getName(), i);
            final File blockFile = new File(fileName);
            try {
                if (!blockFile.createNewFile()) {
                    throw new IOException("file can't create!");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            try (final OutputStream os = new BufferedOutputStream(new FileOutputStream(blockFile))) {
                final int readBytes = is.read(buffer);
                os.write(buffer, 0, readBytes);
            } catch (final IOException e) {
                e.printStackTrace();
                break;
            }
        }

        is.close();

    }

    public static void merge(final String targetFileName, final String... files)
            throws IOException {
        final File file = new File(targetFileName);

        try {
            if (!file.createNewFile()) {
                throw new IOException("can't create file");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        final OutputStream os = new BufferedOutputStream(new FileOutputStream(file));

        for (final String fileName : files) {
            final File f = new File(fileName);

            try (final InputStream is = new BufferedInputStream(new FileInputStream(f))) {
                final byte[] buffer = new byte[4096];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
            } catch (final IOException e) {
                e.printStackTrace();
                break;
            }
        }

        os.close();
    }
}
