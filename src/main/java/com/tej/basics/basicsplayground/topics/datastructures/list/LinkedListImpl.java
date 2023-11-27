package com.tej.basics.basicsplayground.topics.datastructures.list;

import org.springframework.stereotype.Service;

@Service
public class LinkedListImpl<T> implements LinkedList<T>{

    Node<T> head;

    LinkedListImpl() {
        head = new Node<T>();
    }

    @Override
    public Node<T> getFirst() {
        return head;
    }

    @Override
    public Node<T> getLast() {
        Node<T> node = head;
        while(node.next != null) {
            node = node.next;
        }
        return node;
    }

    @Override
    public void addFirst(T item) {
        Node<T> node = new Node<T>();
        node.item = item;
        node.next = head.next;
        head.next = node;
    }

    @Override
    public void addLast(T item) {
        Node<T> last = getLast();
        Node<T> latestLast = new Node<>();
        latestLast.item = item;
        last.next = latestLast;
    }

    @Override
    public void removeFirst() {
        if(head.next != null) {
            head.next = head.next.next;
        }
    }

    @Override
    public void removeLast() {
        Node<T> start = head;
        Node<T> end = head.next;

        while(end != null && end.next != null){
            end = end.next;
            start = start.next;
        }

        start.next = end.next;
    }

    @Override
    public Boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public Integer getSize() {
        int size = 0;
        Node<T> node = head.next;
        while(node != null) {
            node = node.next;
            size++;
        }
        return size;
    }

    @Override
    public String visualize() {
        Node<T> current = head.next;
        StringBuilder sb = new StringBuilder();
        while(current != null) {
            sb.append(current.id + "[" + current.item + "]");
            if(current.next != null) {
                sb.append(" --> ");
                sb.append(current.next.id + "[" + current.next.item + "]");
                sb.append("\n");
            }
            current = current.next;
        }
        return sb.toString();
    }
}
