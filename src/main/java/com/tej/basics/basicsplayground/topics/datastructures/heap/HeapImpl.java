package com.tej.basics.basicsplayground.topics.datastructures.heap;

import org.springframework.stereotype.Service;

import java.lang.reflect.Array;

public class HeapImpl<T extends Comparable<T>> implements Heap<T> {
    T [] arr;
    int filled;
    int firstAvailable;

    public HeapImpl(Class<T> c) {
        arr = (T[]) Array.newInstance(c, capacity);
        filled = 0;
        firstAvailable = 0;
    }

    private void increaseCapacity() {
        T[] newArr = (T[]) Array.newInstance(arr.getClass().componentType(), capacity * 2);
        System.arraycopy(this.arr, 0, newArr, 0, this.arr.length);
    }

    private int parentIndex(int index) {
        return index == 0 ? 0 : (index-1)/2;
    }

    private int leftChildIndex(int index) {
        return 2*index + 1;
    }

    private int rightChildIndex(int index) {
        return 2*index + 2;
    }

    private void swap(int left, int right) {
        T temp = this.arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    @Override
    public void heapifyUp() {
        int currentIndex = this.filled - 1;
        int parentIndex = this.parentIndex(currentIndex);
        boolean proceed = true;

        while(proceed && currentIndex != 0) {
            T currentItem = this.arr[currentIndex];
            T parentItem = this.arr[parentIndex];

            if (parentItem.compareTo(currentItem) < 0) {
                swap(currentIndex, parentIndex);
                currentIndex = parentIndex;
                parentIndex = this.parentIndex(currentIndex);
            } else {
                proceed = false;
            }
        }
    }

    @Override
    public void heapifyDown() {
        if(this.filled == 0) {
            throw new RuntimeException("Heap is empty! Please push at least one item into the heap");
        }
        int parentIndex = 0;

        int leftChildIndex = this.leftChildIndex(parentIndex);
        int rightChildIndex = this.rightChildIndex(parentIndex);
        boolean proceed = true;
        while(proceed && leftChildIndex < this.filled && rightChildIndex < this.filled) {
            T leftChild = this.arr[leftChildIndex];
            T rightChild = this.arr[rightChildIndex];

            int currentIndex = leftChild.compareTo(rightChild) > 0 ? leftChildIndex : rightChildIndex;

            T parentItem = this.arr[parentIndex];
            T currentChild = this.arr[currentIndex];
            if(parentItem.compareTo(currentChild) < 0) {
                this.swap(parentIndex, currentIndex);
                parentIndex = currentIndex;
            } else {
                proceed = false;
            }
            leftChildIndex = this.leftChildIndex(parentIndex);
            rightChildIndex = this.rightChildIndex(parentIndex);
        }

        if(leftChildIndex < this.filled) {
            this.swap(parentIndex, leftChildIndex);
        }

        if(rightChildIndex < this.filled) {
            this.swap(parentIndex, rightChildIndex);
        }
        // you have to set the final entity to null



    }

    @Override
    public void insert(T item) {
        if(this.filled + 1 == capacity) {
            increaseCapacity();
        }
        this.arr[this.filled++] = item;
        this.heapifyUp();
    }

    @Override
    public void delete() {
       if(arr.length == 0)
           throw new RuntimeException("Heap is empty! Please push at least one item into the heap");
       if(arr.length == 1){
           this.filled--;
           return;
       }
       this.arr[0] = this.arr[this.filled - 1 ];
       this.arr[this.filled - 1] = null;
       this.filled--;
       this.heapifyDown();

    }

    @Override
    public T peek() {
        if(arr.length == 0)
            throw new RuntimeException("Heap is empty! Please push at least one item into the heap");
        return this.arr[0];
    }

    @Override
    public T [] print() {
        return this.arr;
    }
}
