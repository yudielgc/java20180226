package com.salesforce;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Box<E> {

    private final E e;

    Box(E e) {
        this.e = e;
    }

    public E getContents() {
        return e;
    }

    public <U> Box<U> map(Function<E, U> f) {
        return new Box<>(f.apply(e));
    }

    public <U> Box<U> flatMap(Function<E, U> f) {
        return new Box<>(f.apply(e));
    }

    public void forEach(Consumer<E> consumer){
        consumer.accept(e);
    }

    public static <V> Box<V> create(Supplier<V> supplier) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Box<>(supplier.get());
    }

}
