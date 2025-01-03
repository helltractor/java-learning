package com.helltractor.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConsulApplication {
    
    @GetMapping("/")
    public String home() {
        return "Hello World!";
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ConsulApplication.class, args);
    }
    
}