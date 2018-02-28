package com.xyzcorp;

import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFuturesTest {

    @Test
    public void testCompletableFuture() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Thread.sleep(3000);


        //dhinojosa@evolutionnext.com

        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 30 + 12;
        }, executorService).thenApplyAsync(integer -> {
            System.out.println(Thread.currentThread().getName());
            return "Hello" + integer;
        }, executorService).thenAcceptAsync(x -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(x);
        }, executorService);

        System.out.println("Done");
        //Thread.sleep(2000); Nothing to do with asychronous
    }

    public CompletableFuture<String> getStockData(String symbol) {
        return CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    URL url = new URL("https://finance.google" +
                            ".com/finance/historical?output=csv&q=" + symbol);
                    InputStream is = url.openConnection().getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader bufferedReader = new BufferedReader(isr);
                    return bufferedReader.lines().collect(Collectors.joining
                            ("\n"));
                } catch (IOException ioe) {
                    throw new RuntimeException(ioe);
                }
            }
        });
    }

    @Test
    public void testParseOfStockDataWithFutures() throws InterruptedException {
        getStockData("CRM")
                .thenApply(s -> (Arrays.stream(s.split("\n")).skip(1)))
                .thenApply(s -> s.map(this::parseLine))
                .thenApply(str -> str.collect(Collectors.joining("\n")))
                .exceptionally(t -> {
                    t.printStackTrace();
                    return "";
                })
                .thenAcceptAsync(System.out::println);

        Thread.sleep(10000);
    }

    private String parseLine(String ln) {
        String[] tokens = ln.split(",");
        try {
            Double close = Double.parseDouble(tokens[4]);
            Double open = Double.parseDouble(tokens[1]);
            return tokens[0] + ": " + (close - open);
        } catch (NumberFormatException nfe) {
            return "Bad Data";
        }
    }

    @Test
    public void testParseStockOfDataWithFuturesThenJustStream() throws
            InterruptedException {
        getStockData("ORCL").exceptionally(t -> {
            t.printStackTrace();
            return "";
        }).thenAcceptAsync(str -> {
            System.out.println(Arrays.stream(str.split("\n"))
                                     .map(this::parseLine)
                                     .collect(Collectors.joining("\n")));
        });
        Thread.sleep(5000);
    }


}
