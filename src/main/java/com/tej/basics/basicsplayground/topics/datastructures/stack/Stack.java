package com.tej.basics.basicsplayground.topics.datastructures.stack;

public interface Stack<T> {
    T pop() throws Exception;

    void push(T item);

    Integer size();

    Boolean isEmpty();

}
