package com.tej.basics.basicsplayground.topics.datastructures.trie;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrieImpl implements Trie {
    TrieNode node;

    TrieImpl() {
        node = new TrieNode();

    }

    @Override
    public List<String> all() {
        return dfs("", this.node);
    }
    @Override
    public void insert(String word) {
        TrieNode curr = this.node;
        for(char c : word.toCharArray()) {
            if(!curr.map.containsKey(c)) {
                curr.map.put(c, new TrieNode());
            }
            curr = curr.map.get(c);
        }
    }

    @Override
    public Boolean contains(String word) {
        if(word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Input argument word: %s cannot be empty or null");
        }
        // 1. Go through and exhaust the word
        TrieNode node = this.node;
        for(Character c : word.toCharArray()) {
            if(!node.map.containsKey(c)) {
                return false;
            }
            node = node.map.get(c);
        }
        return true;
    }

    @Override
    public List<String> getPossibilities(String word) {
        if(word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Input argument word: %s cannot be empty or null");
        }
        // 1. Go through and exhaust the word
        TrieNode node = this.node;
        for(Character c : word.toCharArray()) {
            if(node.map.containsKey(c)) {
                node = node.map.get(c);
            } else {
                return new ArrayList<>();
            }
        }

        // 2. If word exists, add it to possibilities list
        List<String> possibilities = new ArrayList<>(List.of(word));

        // 3. Take the final TrieNode and run DFS,
        possibilities.addAll(dfs(word, node));

        return possibilities;
    }

    private List<String> dfs(String word, TrieNode node) {
        List<String> possibilities = new ArrayList<>();
        StringBuilder sb = new StringBuilder(word);
        for(Character c : node.map.keySet()) {
            sb.append(c);
            possibilities.add(sb.toString());
            possibilities.addAll(dfs(sb.toString(), node.map.get(c)));
            sb.setLength(sb.length() - 1);
        }
        return possibilities;

    }

    @Override
    public List<String> listRelationships() {
        List<String> relationships = new ArrayList<>();
        Queue<TrieNode> queue = new ArrayDeque<>();
        queue.add(this.node);
        while(!queue.isEmpty()) {
            TrieNode node = queue.poll();
            for(Character c : node.map.keySet()) {
                TrieNode innerNode = node.map.get(c);
                for(Character cInner : innerNode.map.keySet()) {
                    relationships.add(node.id + "_" + c + "[" + c + "]" + " --> " + innerNode.id + "_" + cInner + "[" + cInner + "]");
                }
                queue.add(innerNode);
            }
        }
        return relationships;

    }


}
