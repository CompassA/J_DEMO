package com.study.me;

import java.io.InterruptedIOException;

/**
 * @author fanqie
 * @date 2019/11/24
 */
public class SynchronizedDemo {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    /**
     * 死锁
     */
    public void deadLock() {
        final Thread a = new Thread(() -> {
            synchronized (lockA) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB) {
                    System.out.println("A : hello world");
                }
            }
        });

        final Thread b = new Thread(() -> {
           synchronized (lockB) {
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               synchronized (lockA) {
                   System.out.println("B : hello world");
               }
           }
        });

        a.start();
        b.start();
        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
