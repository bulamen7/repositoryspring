package com.bulamen7.learningapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello-world/{name}")
    public String helloWorld(@PathVariable String name) {
        return "Hello " + name;
    }
}