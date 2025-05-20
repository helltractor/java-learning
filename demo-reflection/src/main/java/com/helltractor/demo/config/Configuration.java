package com.helltractor.demo.config;

import com.helltractor.demo.annotation.Bean;
import com.helltractor.demo.entity.Address;
import com.helltractor.demo.entity.Customer;
import com.helltractor.demo.entity.Message;

/**
 * @Author: helltractor
 * @Date: 2024/5/3 18:08
 */
public class Configuration {

    @Bean
    public Customer customer() {
        return new Customer("John", "123132@121");
    }

    @Bean
    public Address address() {
        return new Address("123 Main Street", "12345");
    }

    public Message message() {
        return new Message("Hi there!");
    }

}
