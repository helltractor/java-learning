package com.helltractor.demo.calc;

public class DivisionExpression implements Expression {
    
    private Expression left;
    private Expression right;
    
    public DivisionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    @Override
    public int getValue() {
        if (right.getValue() == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return left.getValue() / right.getValue();
    }
    
}