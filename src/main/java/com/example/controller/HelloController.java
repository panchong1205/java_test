package com.example.controller;

import com.example.model.Hello;
import com.example.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public Hello sayHello(@RequestParam(defaultValue = "World") String name) {
        return helloService.sayHello(name);
    }
}