package com.study.me;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * @author FanQie
 * @date 2019/11/17 10:56
 */
public class FileSplitNioUtil {

    public static String generateBlockFileName(final String srcName, final int fileNo, final String fileType) {
        return srcName + fileNo + fileType;
    }

    /**
     *
     * @param src 无任何后缀的文件
     * @param blockSize 子文件大小
     * @param fileType 文件类型
     * @return 操作是否成功
     */
    public static boolean split(final File src, final int blockSize, final String fileType) {
        boolean status = true;
        //创建缓冲区
        final ByteBuffer byteBuffer = ByteBuffer.allocate(blockSize);
        //初始化输入管道
        try (final FileInputStream fis = new FileInputStream(src);
             final FileChannel inChannel = fis.getChannel()
        ) {
            int fileNo = 0;
            while (inChannel.read(byteBuffer) != -1) {
                //新建输出文件
                final File blockFile = new File(generateBlockFileName(src.getName(), fileNo, fileType));
                if (!blockFile.createNewFile()) {
                    status = false;
                    break;
                }
                //初始化输出管道
                try (final FileOutputStream fos = new FileOutputStream(blockFile);
                     final FileChannel outChannel = fos.getChannel()
                ) {
                    byteBuffer.flip();
                    outChannel.write(byteBuffer);
                    byteBuffer.clear();
                    ++fileNo;
                }
            }
        } catch (final IOException e) {
            status = false;
            e.printStackTrace();
        }
        //将blockSize比特的数据写到新的文件
        return status;
    }

    public static boolean merge(final List<File> fileList, final String destName) {
        boolean status = true;
        //创建目标文件
        final File dest = new File(destName);
        try {
            status = dest.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (!status) {
            return false;
        }

        //创建输出管道
        try (final FileOutputStream fos = new FileOutputStream(dest);
             final FileChannel outChannel = fos.getChannel()) {
            //初始化缓冲区
            final ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

            //将每个file的数据按顺序写至目标文件
            for (final File file : fileList) {
                try (final FileInputStream fis = new FileInputStream(file);
                     final FileChannel inChannel = fis.getChannel()) {
                    while (inChannel.read(byteBuffer) != -1) {
                        byteBuffer.flip();
                        outChannel.write(byteBuffer);
                        byteBuffer.clear();
                    }
                }
            }
        } catch (IOException e) {
            status = false;
            e.printStackTrace();
        }
        return status;
    }
}
