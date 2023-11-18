package com.tej.basics.basicsplayground.topics.datastructures.trie;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrieImpl implements TrieInterface{
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
        }
        return possibilities;

    }
}
