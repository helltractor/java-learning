package com.helltractor.demo.controller;

import com.helltractor.demo.config.TimestampRequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MyController {
    
    @PostMapping("/")
    public Map<String, Object> origin(@RequestBody Map<String, Object> request) {
        return request;
    }
    
    @PostMapping("/timestamp")
    public Map<String, Object> originWithTimestamp(@TimestampRequestBody Map<String, Object> request) {
        return request;
    }
    
}
