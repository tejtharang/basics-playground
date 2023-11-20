package com.tej.basics.basicsplayground.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GreetingController {
/*
*
* */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String getHtml(Model model) {
        return "binary-tree";
    }

    Logger logger = LoggerFactory.getLogger(GreetingController.class);
    @RequestMapping(method = RequestMethod.GET, path = "/greet")
    public String getGreeting() {
        logger.info("hi there");
        return "Hello there!";
    }
}
