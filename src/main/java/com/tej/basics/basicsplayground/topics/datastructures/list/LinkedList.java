package com.tej.basics.basicsplayground.topics.datastructures.list;

public interface LinkedList<T> {
    Node<T> getFirst();
    Node<T> getLast();

    void addFirst(T item);
    void addLast(T item);

    void removeFirst();
    void removeLast();

    Boolean isEmpty();

    Integer getSize();

}
