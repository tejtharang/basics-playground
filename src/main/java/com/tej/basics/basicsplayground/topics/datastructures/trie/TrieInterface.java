package com.tej.basics.basicsplayground.topics.datastructures.trie;

import java.util.List;

public interface TrieInterface {
    List<String> all();
    void insert(String word);

    Boolean contains(String word);

    List<String> getPossibilities(String word);

}
