package com.tej.basics.basicsplayground.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping(value = "/greeting")
public class GreetingController {

    Logger logger = LoggerFactory.getLogger(GreetingController.class);
    @RequestMapping(method = RequestMethod.GET, path = "/greet")
    public String getGreeting() {
        logger.info("hi there");
        return "Hello there!";
    }
}
