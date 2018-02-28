package com.xyzcorp;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureRunner {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture<Integer> integerCompletableFuture =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName());
                    return 30 + 12;
                }, executorService);

        //dhinojosa@evolutionnext.com
        CompletableFuture<String> stringCompletableFuture =
                integerCompletableFuture.thenApplyAsync(integer -> {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                    return "Hello" + integer;
                }, executorService);

        stringCompletableFuture.thenAcceptAsync(x -> {
            System.out.println("Then Accept" + Thread.currentThread().getName());
            System.out.println("Done with Thread" + x);
        }, executorService);

        System.out.println("Done");

        executorService.awaitTermination(13, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}
