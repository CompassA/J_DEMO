package com.study.me.future;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Tomato
 * Created on 2021.06.15
 */
public class CompletionServiceDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                50,
                100,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadPoolExecutor.AbortPolicy());
        CompletionService<Integer> completionService =
                new ExecutorCompletionService<>(
                        threadPool, new ArrayBlockingQueue<>(100));
        for (int i = 0; i < 100; ++i) {
            final int taskNum = i;
            completionService.submit(() -> {
                Thread.sleep(1000);
                if (taskNum % 50 == 0) {
                    throw new Exception();
                }
                return taskNum;
            });
        }
        int cnt = 0;
        for (int i = 0; i < 100; ++i) {
            ++cnt;
            try {
                Integer integer = completionService.take().get();
                System.out.printf("task %d finished\n", integer);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(cnt);
        System.exit(0);
    }
}
