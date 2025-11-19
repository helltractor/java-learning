package com.helltractor.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CloudClientApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CloudClientApplication.class, args);
    }
    
}
