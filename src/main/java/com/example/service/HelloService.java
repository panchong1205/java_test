package com.example.service;

import com.example.model.Hello;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public Hello sayHello(String name) {
        return new Hello("Hello, " + name + "!");
    }
}