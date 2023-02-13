package com.protoss.myfirstspring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @GetMapping("/hellorest")
    public String HelloWorld() {
        return "Hello World from the REST Controller!";
    }
}
