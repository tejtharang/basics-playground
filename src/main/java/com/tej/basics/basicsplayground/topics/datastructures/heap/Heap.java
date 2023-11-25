package com.tej.basics.basicsplayground.topics.datastructures.heap;

public interface Heap<T extends Comparable<T>> {
    int capacity = 100;
    void heapifyUp();

    void heapifyDown();

    void insert(T item);

    void delete();

    T [] print();

    T peek();
}
