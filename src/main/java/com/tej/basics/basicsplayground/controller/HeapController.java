package com.tej.basics.basicsplayground.controller;

import com.tej.basics.basicsplayground.topics.datastructures.heap.Heap;
import com.tej.basics.basicsplayground.topics.datastructures.heap.HeapImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/heap")
public class HeapController {

    Heap<Integer> heap;

    HeapController(){
        heap = new HeapImpl<>(Integer.class);
    }
    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public @ResponseBody void insert(@RequestParam("number") Integer param) {
        heap.insert(param);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public @ResponseBody void delete() {
        heap.delete();
    }

    @RequestMapping(path = "/print", method = RequestMethod.GET)
    public @ResponseBody Integer[] print() {
        return heap.print();
    }

    @RequestMapping(path = "/peek", method = RequestMethod.GET)
    public @ResponseBody Integer peek() {
        return heap.peek();
    }

    @RequestMapping(path = "/visualize", method = RequestMethod.GET)
    public String visualize(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("payload", heap.visualize());
        return "heap";
    }
}
