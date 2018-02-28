package com.salesforce;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CompletableFuturesTest {

    @Test
    public void testCompletableFuture() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println(Thread.currentThread().getName());
                return 30 + 12;
            }
        }, executorService);

        CompletableFuture<String> stringCompletableFuture = integerCompletableFuture.thenApply(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "Hello " + integer;
            }
        });

        stringCompletableFuture.thenAccept(System.out::println);
        System.out.println("Done");
        Thread.sleep(2000);
    }

    @Test
    public void testParseOfStockData() throws Exception {
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

    CompletableFuture<String> getStockData(String symbol) throws IOException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL("https://finance.google.com/finance/historical?output=csv&q=" + symbol);

                InputStream inputStream = url.openConnection().getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                return bufferedReader.lines().collect(Collectors.joining("\n"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
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
}
