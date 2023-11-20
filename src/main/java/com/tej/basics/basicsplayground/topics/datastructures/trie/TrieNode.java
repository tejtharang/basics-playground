package com.tej.basics.basicsplayground.topics.datastructures.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TrieNode {
    String id;
    Map<Character, TrieNode> map;

    TrieNode() {
        id = UUID.randomUUID().toString();
        map = new HashMap<>();
    }
}
