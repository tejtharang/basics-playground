package com.tej.basics.basicsplayground.topics.datastructures.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueueImpl<T> implements QueueInterface<T>{

    Deque<T> queue;

    QueueImpl() {
        queue = new ArrayDeque<>();
    }
    @Override
    public void push(T item) {
        queue.push(item);
    }

    @Override
    public T poll() {
        return queue.poll();
    }

    @Override
    public T peek() {
        return queue.peek();
    }

    @Override
    public Integer size() {
        return queue.size();
    }

    @Override
    public Boolean isEmpty() {
        return queue.isEmpty();
    }
}
