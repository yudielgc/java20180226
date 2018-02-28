package com.xyzcorp;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Box<E> {
    private final E e;

    public Box(E e) {
        this.e = e;
    }

    public E getContents() {
        return e;
    }

    public <U> Box<U> map(Function<? super E, ? extends U> f) {
        return new Box<>(f.apply(e));
    }

    public <U> Box<U> flatMap(Function<? super E, ? extends Box<U>> f) {
       return f.apply(e);
    }

	public void foreach(Consumer<? super E> consumer) {
	    consumer.accept(e);			
	}


    public static <V> Box<V> create(Supplier<? extends V> supplier) {
        return new Box<>(supplier.get());
    }
}


