package com.tej.basics.basicsplayground.topics.datastructures.tree;

import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Service
public class BinaryTreeImpl implements BinaryTree{

    Node head;

    BinaryTreeImpl() {
        head = new Node();
    }
    @Override
    public void insert(Integer value) {
        if(value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        insertBfs(head, value);
    }

    private void insertBfs(Node node, Integer value) {
        if(node.getVal() == null) {
            node.setVal(value);
            return;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);

        while(!queue.isEmpty()) {
            Node queueHead = queue.poll();
            Node insertionNode = new Node();
            insertionNode.setVal(value);

            if (queueHead.left == null) {
                queueHead.left = insertionNode;
                return;
            }

            if (queueHead.right == null) {
                queueHead.right = insertionNode;
                return;
            }

            queue.add(queueHead.left);
            queue.add(queueHead.right);
        }

    }

    @Override
    public void remove(Integer value) {
        if(value == null) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }

        // find right most element value
        Node current = head;
        Node parent = null;
        while(current !=null && current.right!=null) {
            parent = current;
            current = current.right;
        }

        // Set rightmost.left = parent.right
        parent.right = current.left;
        Integer replaceVal = current.val;

        // find the element you want to replace
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(head);

        while(!queue.isEmpty()) {
            Node queueHead = queue.poll();
            if(queueHead.val == value) {
                queueHead.val = replaceVal;
                return;
            }
            if(queueHead.left != null) queue.add(queueHead.left);
            if(queueHead.right != null) queue.add(queueHead.right);
        }
    }

    @Override
    public Boolean search(Integer value, SearchType searchType) {
        return switch (searchType) {
            case BFS -> bfs(value);
            case DFS -> dfs(head, value);
        };
    }

    private Boolean dfs(Node node, Integer value) {
        if(value == null) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        if(node == null) {
            return false;
        }

        if(node.getVal() == null) {
            throw new RuntimeException("Illegal node with null value found");
        }

        if(node.getVal().equals(value)) {
            return true;
        }

        return dfs(node.getLeft(), value) || dfs(node.getRight(), value);
    }

    private Boolean bfs(Integer value) {
        if(value == null) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(head);

        while(!queue.isEmpty()) {
            Node queueHead = queue.poll();
            if(queueHead.getVal() == null) {
                throw new RuntimeException("Illegal node with empty value");
            }

            if(queueHead.getVal() == value) {
                return true;
            }

            if(queueHead.getLeft() != null) queue.add(queueHead.getLeft());
            if(queueHead.getRight() != null) queue.add(queueHead.getRight());
        }

        return false;
    }

    @Override
    public List<Integer> traverse(Order order) {
        switch (order) {
            case PREORDER -> { return traversePreOrder(head); }
            case POSTORDER -> { return traversePostOrder(head); }
            default -> { return traverseInorder(head); }
        }
    }

    public List<Node> nodeList() {
        Node node = head;
        return nodeList(node);
    }

    private List<Node> nodeList(Node node) {
        List<Node> ret = new ArrayList<>();
        if(node == null)
            return ret;
        if(node.getVal() == null) {
            throw new RuntimeException("Illegal node found!");
        }

        ret.addAll(nodeList(node.getLeft()));
        ret.add(node);
        ret.addAll(nodeList(node.getRight()));
        return ret;

    }

    private List<Integer> traverseInorder(Node node) {
        List<Integer> ret = new ArrayList<>();
        if(node == null)
            return ret;
        if(node.getVal() == null) {
            throw new RuntimeException("Illegal node found!");
        }

        ret.addAll(traverseInorder(node.getLeft()));
        ret.add(node.getVal());
        ret.addAll(traverseInorder(node.getRight()));
        return ret;

    }

    private List<Integer> traversePostOrder(Node node) {
        List<Integer> ret = new ArrayList<>();
        if(node == null)
            return ret;
        if(node.getVal() == null) {
            throw new RuntimeException("Illegal node found!");
        }

        ret.addAll(traversePostOrder(node.getLeft()));
        ret.addAll(traversePostOrder(node.getRight()));
        ret.add(node.getVal());
        return ret;
    }

    private List<Integer> traversePreOrder(Node node) {
        List<Integer> ret = new ArrayList<>();
        if(node == null)
            return ret;
        if(node.getVal() == null) {
            throw new RuntimeException("Illegal node found!");
        }

        ret.add(node.getVal());
        ret.addAll(traversePreOrder(node.getLeft()));
        ret.addAll(traversePreOrder(node.getRight()));
        return ret;
    }


}
