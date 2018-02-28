package com.xyzcorp;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static org.assertj.core.api.Assertions.assertThat;

public class BoxTest {

    @Test
    public void testBoxInstantiation() {
        Box<Integer> bi = new Box<>(4);
        assertThat(bi.getContents()).isEqualTo(4);
//        Box<String> bs = new Box<>("Foo");
//        assertThat(bs.getContents()).isEqualTo("Foo");
    }

    @Test
    public void testRepresentationOfBox() {
        Box<Integer> bi = new Box<>(4);
        System.out.println(bi.getClass().getName());
        System.out.println(Arrays.asList(bi.getClass().getGenericInterfaces()));
        System.out.println(Arrays.asList(bi.getClass().getGenericSuperclass()));
    }

    @Test
    public void testMapFunction() {
        List<Integer> result = Stream.of(1, 2, 3, 4)
                                     .map(integer -> integer + 4)
                                     .filter(x -> x % 2 == 0)
                                     .peek(System.out::println)
                                     .collect(Collectors.toList());

        assertThat(result).containsExactly(6, 8);
    }

    @Test
    public void testBoxMap() {
        Box<String> bs = new Box<>("Hello");
        Box<Integer> result = bs.map(String::length);
        assertThat(result.getContents()).isEqualTo(5);
    }

    @Test
    public void testBoxForEach() {
        Box<String> bs = new Box<>("Hello");
        bs.map(String::length)
          .foreach(System.out::println);
    }

    @Test
    public void testFlatMap() {
        Stream<String> stringStream = Stream.of("My uncle has a country place",
                "That no one knows about",
                "He says it used to be a farm",
                "Before the Motor Law",
                "And on Sundays I elude the eyes",
                "And hop the turbine freight",
                "To far outside the wire",
                "Where my white-haired uncle waits");
        Map<String, Long> result = stringStream
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(identity(), counting()));
        System.out.println(result);
    }

    @Test
    public void testParallel() {
        //IntStream.range(0, 10000000).boxed()
        Stream<String> stringStream = Stream.of("My uncle has a country place",
                "That no one knows about",
                "He says it used to be a farm",
                "Before the Motor Law",
                "And on Sundays I elude the eyes",
                "And hop the turbine freight",
                "To far outside the wire",
                "Where my white-haired uncle waits");

       // Stream<String> stringStream = Stream.empty();
        Optional<String> first = stringStream
                .peek(s -> printCurrentThread("1"))
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .peek(s -> printCurrentThread("2"))
                .map(String::toLowerCase)
                .peek(s -> printCurrentThread("3"))
                .parallel()
                .findFirst();

        System.out.println(first.orElse("Stream has no content"));
    }

	private void printCurrentThread(String label) {
		System.out.println(label + ": " + Thread.currentThread().getName());
	}

	@Test
    public void testFlatMapOptional() {
        Optional<Integer> integerBox = Optional.of(40);
        Optional<Integer> result1 = integerBox.map(i -> i + 4);
        Optional<Integer> result2 = integerBox.flatMap(i -> Optional.of(i + 4));
    }

	@Test
	public void testBoxFlatMap() {
       Box<String> bs = new Box<>("Foo");
       Box<Integer> bmap = bs.map(String::length);
       Box<Integer> bfmap = bs.flatMap(s -> new Box<>(s.length()));
    }

    @Test
    public void testBoxFactory() {
        System.out.println("1:" + LocalDateTime.now());
        Box<LocalDateTime> result = Box.create(LocalDateTime::now);
        System.out.println("2:" + LocalDateTime.now());
        System.out.println("3:" + result.getContents());
    }

    @Test
    public void streamOfPersons() {
        List<American> collect = Stream.of(new American(), new American()).map
                (american -> new
                Miamian()).collect(Collectors.toList());
    }

    @Test
    public void testTypesToBeDetermined() {
        //Stream.of("Foo", "Bar", "Baz").flatMap(x -> )
    }
}








