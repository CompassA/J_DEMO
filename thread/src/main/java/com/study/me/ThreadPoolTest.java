package com.study.me;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池参数
 * 核心线程数
 * 最大线程数
 * 空闲线程存活时间(非核心线程)
 * 时间单位
 * 阻塞队列
 * 线程创建工厂
 * 拒绝策略
 * @author fanqie
 * @date 2020/4/20
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());

        final int coreThreadNum = 5;
        final int maxThreadNum = 10;
        final long aliveTime = 0;
        final TimeUnit unit = TimeUnit.SECONDS;
        final BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(5);
        final ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("my-test-%d")
                .build();
        final RejectedExecutionHandler handler1 = new ThreadPoolExecutor.AbortPolicy();
        final RejectedExecutionHandler handler2 = new ThreadPoolExecutor.CallerRunsPolicy();
        final RejectedExecutionHandler handler3 = new ThreadPoolExecutor.DiscardOldestPolicy();
        final RejectedExecutionHandler handler4 = new ThreadPoolExecutor.DiscardPolicy();

        final ThreadPoolExecutor pool = new ThreadPoolExecutor(
                coreThreadNum,
                maxThreadNum,
                aliveTime,
                unit,
                taskQueue,
                threadFactory,
                handler4);

        pool.prestartAllCoreThreads();

        for (int i = 0; i < 16; ++i) {
            pool.execute(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(50000);
        pool.shutdown();
    }
}
