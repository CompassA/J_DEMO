package com.study.me;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author fanqie
 * @date 2020/4/14
 */
public class MyThreadPool {

    private final Queue<Runnable> tasks;

    private final List<Worker> workers;

    public MyThreadPool(final int workerNums) {
        tasks = new LinkedList<>();
        workers = new ArrayList<>(workerNums);
        for (int i = 0; i < workerNums; ++i) {
            final Worker worker = new Worker();
            workers.add(worker);
            final Thread thread = new Thread(worker, "ThreadWork:" + i);
            thread.setDaemon(true);
            thread.start();
        }
    }

    public void execute(final Runnable task) {
        if (task != null) {
            synchronized (tasks) {
                tasks.offer(task);
                tasks.notify();
            }
        }
    }

    public void shutDown() {
        workers.forEach(Worker::stop);
    }

    private class Worker implements Runnable {

        private volatile boolean isRunning = true;

        @Override
        public void run() {
            while (isRunning) {
                final Runnable task;
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = tasks.poll();
                }
                if (task != null) {
                    System.out.printf("工作线程%s领取任务并执行,线程池剩余任务数量%d\n", Thread.currentThread().getName(), tasks.size());
                    task.run();
                }
            }
        }

        public void stop() {
            isRunning = false;
        }
    }

    public static void main(String[] args) {
        final int taskNum = 300;
        MyThreadPool pool = new MyThreadPool(20);
        final int[] succeed = new int[1];
        for (int i = 0; i < taskNum; ++i) {
            pool.execute(() -> {
                System.out.println("Task Start!");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task End!");
                ++succeed[0];
            });
        }

        while (succeed[0] != taskNum) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pool.shutDown();
    }
}
