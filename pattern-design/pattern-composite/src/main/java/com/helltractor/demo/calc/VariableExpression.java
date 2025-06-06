package com.helltractor.demo.calc;

import java.util.Map;

public class VariableExpression implements Expression {

    private final String name;
    private final int value;

    public VariableExpression(String name, Map<String, Integer> variables) {
        this.name = name;
        this.value = variables.getOrDefault(name, 0);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + " = " + value;
    }
    
}