package com.tej.basics.basicsplayground.controller;

import com.tej.basics.basicsplayground.topics.datastructures.tree.Node;
import com.tej.basics.basicsplayground.topics.datastructures.trie.Trie;
import com.tej.basics.basicsplayground.topics.datastructures.trie.TrieImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/trie")
public class TrieController {
    @Autowired
    TrieImpl trie;

    @RequestMapping(path = "/{word}", method = RequestMethod.POST)
    public @ResponseBody void insertWord(@PathVariable("word") String word) {
        trie.insert(word);
    }

    @RequestMapping(path = "/{word}/search", method = RequestMethod.GET)
    public @ResponseBody Boolean contains(@PathVariable("word") String word) {
        return trie.contains(word);
    }

    @RequestMapping(path = "/{word}/possibilities", method = RequestMethod.GET)
    public @ResponseBody List<String> getPossibilities(@PathVariable("word") String word) {
        return trie.getPossibilities(word);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public @ResponseBody List<String> getPossibilities() {
        return trie.all();
    }

    @RequestMapping(value = "/visualize", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String visualize(@ModelAttribute("model") ModelMap model) {

        model.addAttribute("relationships", trie.listRelationships());
        return "trie";
    }
}
