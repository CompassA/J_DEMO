package com.study.me.base;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms10m
 * -Xmx10m
 * -XX:NewSize=1m
 * -XX:MaxNewSize=1m
 * -XX:HeapDumpOnOutOfMemoryError
 * -verbose:gc
 * -Xloggc:gc_log
 * -XX:+PrintGCDetails
 * @author fanqie
 * @date 2020/4/10
 */
public class HeapOutOfMemoryTest {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>(0);
        int cnt = 0;
        while (true) {
            System.out.printf("%d\n", ++cnt);
            list.add(new byte[1000]);
        }
    }
}
