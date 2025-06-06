package com.helltractor.demo.calc;

public abstract class BinaryOperationExpression implements Expression {

    private Expression left;
    private Expression right;
    
    protected BinaryOperationExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
}