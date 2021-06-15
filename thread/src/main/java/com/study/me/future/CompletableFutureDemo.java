package com.study.me.future;

import java.util.concurrent.CompletableFuture;

/**
 * @author Tomato
 * Created on 2021.06.15
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i = 0; i < 10000; ++i) {
                sum += i;
            }
            return sum;
        }).exceptionally(e -> {
            e.printStackTrace();
            return 0;
        }).thenAccept(System.out::println);


        CompletableFuture.runAsync(() -> {
            System.out.println("hello");
            throw new RuntimeException();
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
    }
}
