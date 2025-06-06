package com.helltractor.demo.calc;

public class AddExpression implements Expression {

    private Expression left;
    private Expression right;
    
    public AddExpression (Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public int getValue() {
        return left.getValue() + right.getValue();
    }
    
}