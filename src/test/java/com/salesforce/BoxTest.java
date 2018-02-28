package com.salesforce;

import org.junit.Test;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.counting;
import static org.assertj.core.api.Assertions.assertThat;

public class BoxTest {

    @Test
    public void testBoxInstantiation() throws Exception {
        Box<Integer> boxInteger = new Box<Integer>(4);
        assertThat(boxInteger.getContents()).isEqualTo(4);

        Box<String> boxString = new Box<String>("Foo");
        assertThat(boxString.getContents()).isEqualTo("Foo");
    }

    @Test
    public void testRepresentationOfBox() throws Exception {
        Box<Integer> boxInteger = new Box<>(4);

        System.out.println(boxInteger.getClass().getName());
        System.out.println(boxInteger.getClass().getGenericInterfaces());
        System.out.println(boxInteger.getClass().getGenericSuperclass());
    }

    @Test
    public void testMapFunction() throws Exception {
        Stream.of(1, 2, 3, 4).map(integer -> integer + 4).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer x) {
                return x % 2 == 0;
            }
        });
    }

    @Test
    public void testMapFunctionLambda() throws Exception {
        List<Integer> result = Stream.of(1, 2, 3, 4)
                .map(integer -> integer + 4)
                .filter(x -> x % 2 == 0)
                .peek(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        System.out.println(integer);
                    }
                })
                .collect(Collectors.toList());

        assertThat(result).containsExactly(6, 8);
    }

    @Test
    public void textBoxMap() throws Exception {
        Box<String> boxString = new Box<>("Hello");
        Box<Integer> result = boxString.map(String::length);

        assertThat(result.getContents()).isEqualTo(5);
    }

    @Test
    public void testBoxForEach() throws Exception {
        Box<String> boxString = new Box<>("Hello");

        boxString.map(String::length)
                .forEach(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }

    @Test
    public void testFlapMap() throws Exception {
        Stream<String> stringStream = Stream.of("My uncle has a country place",
                "That no one knows about",
                "He says it used to be a farm",
                "Before the Motor Law",
                "And on Sundays I elude the eyes",
                "And hop the turbine freight",
                "To far outside the wire",
                "Where my white-haired uncle waits");

        //Map<String, List<String>> results =
        Map<String, Long> results = stringStream
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .map(String::toLowerCase)
                //.sorted()
                //.sorted(Comparator.reverseOrder())
                //.forEach(x -> System.out.println(x))
                .collect(Collectors.groupingBy(s -> s, counting()));
        //.collect(Collectors.groupingBy(identity()));

        System.out.println(results);
    }

    @Test
    public void testParallelWithCollect() throws Exception {
        Stream<String> stringStream = Stream.of("My uncle has a country place",
                "That no one knows about",
                "He says it used to be a farm",
                "Before the Motor Law",
                "And on Sundays I elude the eyes",
                "And hop the turbine freight",
                "To far outside the wire",
                "Where my white-haired uncle waits");

        Map<String, Long> results = stringStream
                .peek(s -> printCurrentThread("1"))
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .parallel()
                .peek(s -> printCurrentThread("2"))
                .map(String::toLowerCase)
                .peek(s -> printCurrentThread("3"))
                .collect(Collectors.groupingBy(identity(), counting()));

        System.out.println(results);
    }

    @Test
    public void testParallel() throws Exception {
        Stream<String> stringStream = Stream.of("My uncle has a country place",
                "That no one knows about",
                "He says it used to be a farm",
                "Before the Motor Law",
                "And on Sundays I elude the eyes",
                "And hop the turbine freight",
                "To far outside the wire",
                "Where my white-haired uncle waits");
        //stringStream = Stream.empty();

        Optional<String> results = stringStream
                .peek(s -> printCurrentThread("1"))
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .peek(s -> printCurrentThread("2"))
                .map(String::toLowerCase)
                .peek(s -> printCurrentThread("3"))
                .parallel()
                .findFirst();

        System.out.println(results.orElse("Stream has no content"));
    }

    @Test
    public void testFlatMapOption() throws Exception {
        Optional<Integer> boxInteger = Optional.of(40);
        Optional<Integer> boxMap = boxInteger.map(i -> i +4);
        Optional<Integer> boxFlatMap = boxInteger.flatMap(i -> Optional.of(i + 5));
    }

    @Test
    public void testBoxFlatMap() throws Exception {
        Box<String> boxString = new Box<>("Foo");
        Box<Integer> boxMap = boxString.map(String::length);
        //Box<Integer> boxFlatMap = boxString.flatMap(s -> new Box<>(s.length()));
    }

    @Test
    public void testBoxFactory() throws Exception {
        System.out.println("1: " + LocalDateTime.now());
        Box<LocalDateTime> result = Box.create(LocalDateTime::now);
        System.out.println("2: " + LocalDateTime.now());
        System.out.println("3: " + result.getContents());
    }

    private void printCurrentThread(String label) {
        System.out.println(label + ": " + Thread.currentThread().getName());
    }
}
