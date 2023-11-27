package com.tej.basics.basicsplayground.controller;

import com.tej.basics.basicsplayground.topics.datastructures.list.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/list")
public class ListController {

    Logger logger = Logger.getLogger(ListController.class.getName());
    @Autowired
    private LinkedList<Integer> list;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public @ResponseBody void insert(@RequestParam("value") Integer value) {
        list.addLast(value);
    }

    @RequestMapping(value = "/size", method = RequestMethod.GET)
    public @ResponseBody int size() {
        return list.getSize();
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public @ResponseBody void remove() {
        list.removeLast();
    }

    @RequestMapping(value = "/visualize", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String visualize(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("payload", list.visualize());
        return "list";
    }



}
