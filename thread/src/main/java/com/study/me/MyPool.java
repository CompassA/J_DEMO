package com.study.me;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

/**
 * @author fanqie
 * @date 2020/4/13
 */
public class MyPool<T> {

    private final Object lock;

    private final Queue<T> objPool;

    public MyPool(int capacity, Supplier<T> supplier) {
        lock = new Object();
        objPool = new LinkedList<>();
        for (int i = 0; i < capacity; ++i) {
            objPool.offer(supplier.get());
        }
    }

    public T getObj(final long mills) throws InterruptedException {
        synchronized (lock) {
            //calculate the deadline
            final long future = System.currentTimeMillis() + mills;
            long remains = mills;

            //waiting
            while (objPool.isEmpty() && remains > 0) {
                lock.wait(mills);
                remains = future - System.currentTimeMillis();
            }

            //do
            final T result = objPool.poll();
            System.out.printf("pool size: %d\n", objPool.size());
            return result;
        }
    }

    public void release(final T obj) {
        synchronized (lock) {
            objPool.offer(obj);
            lock.notifyAll();
            System.out.printf("pool size: %d\n", objPool.size());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final MyPool<Object> pool = new MyPool<>(10, Object::new);
        final CountDownLatch start = new CountDownLatch(1);
        final int threadNums = 30;
        final CountDownLatch end = new CountDownLatch(threadNums);

        final int[] success = new int[1];
        final int[] fail = new int[1];
        for (int i = 0; i < threadNums; ++i) {
            new Thread(() -> {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    final Object obj = pool.getObj(3000);
                    if (obj != null) {
                        System.out.printf("线程[%s]成功获取！", Thread.currentThread().getName());
                        success[0]++;
                        try {
                            Thread.currentThread().sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        pool.release(obj);
                    } else {
                        System.out.printf("线程[%s]获取失败！", Thread.currentThread().getName());
                        fail[0]++;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                end.countDown();
            }).start();
        }
        start.countDown();
        end.await();
        System.out.printf("成功:%d, 失败:%d\n", success[0], fail[0]);
    }
}
