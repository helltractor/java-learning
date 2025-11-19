package com.helltractor.demo.calc;

public class NumberExpression implements Expression {
    
    private int value;
    
    public NumberExpression(int value) {
        this.value = value;
    }
    
    @Override
    public int getValue() {
        return value;
    }
    
}