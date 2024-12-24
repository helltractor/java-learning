package com.helltractor.demo.controller;

import com.helltractor.demo.service.SimpleClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleClientController {
    
    @Autowired
    private SimpleClientService simpleClientService;
    
    @RequestMapping("/hello")
    public String printHelloMessage(@RequestParam(defaultValue = "Helltractor") final String name) {
        return this.simpleClientService.sendHelloMessage(name);
    }
    
    @RequestMapping("/bye")
    public String printByeMessage(@RequestParam(defaultValue = "Helltractor") final String name) {
        return this.simpleClientService.sendByeMessage(name);
    }
    
}