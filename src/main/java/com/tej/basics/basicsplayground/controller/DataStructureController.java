package com.tej.basics.basicsplayground.controller;

import com.tej.basics.basicsplayground.topics.datastructures.trie.TrieImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data-structures")
public class DataStructureController {
    @Autowired
    TrieImpl trie;

    @RequestMapping(path = "/trie/{word}", method = RequestMethod.POST)
    public void insertWord(@PathVariable("word") String word) {
        trie.insert(word);
    }

    @RequestMapping(path = "/trie/{word}/search", method = RequestMethod.GET)
    public Boolean contains(@PathVariable("word") String word) {
        return trie.contains(word);
    }

    @RequestMapping(path = "/trie/{word}/possibilities", method = RequestMethod.GET)
    public List<String> getPossibilities(@PathVariable("word") String word) {
        return trie.getPossibilities(word);
    }

    @RequestMapping(path = "/trie", method = RequestMethod.GET)
    public List<String> getPossibilities() {
        return trie.all();
    }




}
