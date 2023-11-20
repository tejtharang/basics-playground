package com.tej.basics.basicsplayground.topics.datastructures.tree;

import java.util.List;

public interface BinaryTree {
    /*
    *   Inserts at the first available slot
    * */
    void insert(Integer value);

    /*
    *   If removed Node has child,
    *   1. promote the left and delete child
    *   2. If no left child, promote right and delete child
    * */
    void remove(Integer value);

    Boolean search(Integer value, SearchType searchType);

    List<Integer> traverse(Order order);

    List<Node> nodeList();

    enum Order {
        INORDER, PREORDER, POSTORDER
    }

    enum SearchType {
        DFS, BFS
    }
}
