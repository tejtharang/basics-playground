package com.tej.basics.basicsplayground.controller;

import com.tej.basics.basicsplayground.topics.datastructures.heap.Heap;
import com.tej.basics.basicsplayground.topics.datastructures.heap.HeapImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
