package com.tej.basics.basicsplayground.controller;

import com.tej.basics.basicsplayground.topics.datastructures.tree.BinaryTree;
import com.tej.basics.basicsplayground.topics.datastructures.tree.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/binary-tree")
public class BinaryTreeController {
    @Value("${msg.title}")
    String title;
    @Autowired
    BinaryTree binaryTree;


    @RequestMapping(path = "/insert/{value}", method = RequestMethod.POST)
    public @ResponseBody void insert(@RequestParam("value") Integer value) {
        binaryTree.insert(value);
    }

    @RequestMapping(path = "/remove/{value}", method = RequestMethod.DELETE)
    public @ResponseBody void remove(@RequestParam("value") Integer value) {
        binaryTree.remove(value);
    }

    @RequestMapping(path = "/traverse/{order}", method = RequestMethod.GET)
    public @ResponseBody List<Integer> traverse(@RequestParam("order") BinaryTree.Order order) {
        return binaryTree.traverse(order);
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public @ResponseBody Boolean search(@RequestParam("value") Integer value, @RequestParam("search-type") BinaryTree.SearchType searchType) {
        return binaryTree.search(value, searchType);
    }

    /*
    * Controller vs RestController is a very important distinction to make when visualizing images
    *
    * */

    @RequestMapping(value = "/visualize", method = RequestMethod.GET)
    public String visualize(@ModelAttribute("model") ModelMap model) {
        List<Node> nodes = binaryTree.nodeList();
        model.addAttribute("nodes", nodes);
        return "binary-tree";
    }
}
