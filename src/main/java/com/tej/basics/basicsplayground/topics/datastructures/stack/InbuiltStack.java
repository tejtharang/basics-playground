package com.tej.basics.basicsplayground.topics.datastructures.stack;

import java.util.Stack;
public class InbuiltStack<T> implements com.tej.basics.basicsplayground.topics.datastructures.stack.Stack<T> {
    Stack<T> stack = new Stack<T>();


    @Override
    public T pop() throws Exception {
        return stack.pop();
    }

    @Override
    public void push(T item) {
        stack.push(item);
    }

    @Override
    public Integer size() {
        return stack.size();
    }

    @Override
    public Boolean isEmpty() {
        return stack.isEmpty();
    }
}
