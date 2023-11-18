package com.tej.basics.basicsplayground.topics.datastructures.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Map<Character, TrieNode> map;

    TrieNode() {
        map = new HashMap<>();
    }
}
