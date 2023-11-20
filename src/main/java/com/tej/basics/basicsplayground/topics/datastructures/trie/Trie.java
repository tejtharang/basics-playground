package com.tej.basics.basicsplayground.topics.datastructures.trie;

import java.util.List;

public interface Trie {
    List<String> all();

    List<String> listRelationships();
    void insert(String word);

    Boolean contains(String word);

    List<String> getPossibilities(String word);

}
