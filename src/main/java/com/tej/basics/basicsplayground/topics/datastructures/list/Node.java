package com.tej.basics.basicsplayground.topics.datastructures.list;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

public class Node<T> {
    T item;
    Node<T> next;

    String id;

    public Node() {
        id = UUID.randomUUID().toString();
    }
}
