package com.helltractor.demo.calc;

public class SubtractExpression implements Expression {

    private Expression left;
    private Expression right;

    public SubtractExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int getValue() {
        return left.getValue() - right.getValue();
    }
    
}