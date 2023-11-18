package com.tej.basics.basicsplayground.topics.datastructures.stack;

import java.util.ArrayList;
import java.util.List;

public class StackImpl<T> implements Stack<T>{

    List<T> list;

    StackImpl() {
        list = new ArrayList<>();
    }

    @Override
    public T pop() throws Exception {
        if(!list.isEmpty()) {
            int index = list.size() - 1;
            T ret = list.get(index);
            list.remove(index);
            return ret;
        } else {
            throw new Exception();
        }

    }

    @Override
    public void push(T item) {
        list.add(item);
    }

    @Override
    public Integer size() {
        return list.size();
    }

    @Override
    public Boolean isEmpty() {
        return list.isEmpty();
    }
}
