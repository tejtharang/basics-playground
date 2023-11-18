package com.tej.basics.basicsplayground.topics.datastructures.queue;

public interface QueueInterface<T> {
    void push(T item);

    T poll();

    T peek();

    Integer size();

    Boolean isEmpty();
}
