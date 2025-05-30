package com.helltractor.demo.model;

public class User {
    
    private final String name;
    
    private final int age;
    
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String toString() {
        return "User{name='" + name + "', age=" + age + '}';
    }
    
}
