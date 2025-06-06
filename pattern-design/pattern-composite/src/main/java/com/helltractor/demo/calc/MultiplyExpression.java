package com.helltractor.demo.calc;

public class MultiplyExpression implements Expression {

    private Expression left;
    private Expression right;

    public MultiplyExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int getValue() {
        return left.getValue() * right.getValue();
    }
    
}