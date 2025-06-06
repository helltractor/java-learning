package com.helltractor.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.helltractor.demo.service.HelloService;

@RestController
public class HelloController {

    @Autowired
    private HelloService service;

    @GetMapping(value = "/hello/{name}")
    public String apiHello(@PathVariable("name") String name) {
        return service.sayHello(name);
    }

}
