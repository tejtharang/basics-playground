package com.tej.basics.basicsplayground.topics.datastructures.list;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class Node<T> {
    T item;
    Node<T> next;
}
