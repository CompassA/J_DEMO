package com.study.me.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * @author fanqie
 * @date 2020/4/6
 */
public class Connection2 {

    private volatile static Connection2 INSTANCE = null;

    public static Connection2 getInstance() {
        if (INSTANCE == null) {
            synchronized (Connection2.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Connection2();
                }
            }
        }
        return INSTANCE;
    }

    private Connection2() {
        if (INSTANCE != null) {
            throw new IllegalStateException();
        }
    }

    /**----------------------------------------*/
    public static void main(String[] args) throws InterruptedException {
        final Connection2[] var1 = new Connection2[1];
        final Connection2[] var2 = new Connection2[1];

        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            try {
                var1[0] = Connection2.getInstance();
            } finally {
                countDownLatch.countDown();
            }
        }).start();

        new Thread(() -> {
            try {
                var2[0] = Connection2.getInstance();
            } finally {
                countDownLatch.countDown();
            }
        }).start();

        countDownLatch.await();

        System.out.println(var1[0] == var2[0]);

    }
}
