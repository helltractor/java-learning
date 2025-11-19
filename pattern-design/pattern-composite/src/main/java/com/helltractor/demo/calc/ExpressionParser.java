package com.helltractor.demo.calc;

import java.util.*;

public class ExpressionParser {
    
    private final String expression;
    private final Map<String, Integer> variables = new HashMap<>();
    private int point = 0;
    
    public ExpressionParser(String expression) {
        this.expression = expression;
    }
    
    public ExpressionParser(String expression, Map<String, Integer> variables) {
        this.expression = expression;
        this.variables.putAll(variables);
    }
    
    public List<String> toSuffix() {
        List<String> suffix = new ArrayList<>();
        List<String> stack = new ArrayList<>();
        
        while (point < expression.length()) {
            char c = expression.charAt(point);
            if (Character.isWhitespace(c)) {
                point++;
                continue;
            } else if (c == '(') {
                stack.add(String.valueOf(c));
            } else if (c == ')') {
                while (!stack.getLast().equals("(")) {
                    suffix.add(stack.removeLast());
                }
                stack.removeLast();
            } else if (c == '*' || c == '/') {
                while (!stack.isEmpty() && (stack.getLast().equals("*") || stack.getLast().equals("/"))) {
                    suffix.add(stack.removeLast());
                }
                stack.add(String.valueOf(c));
            } else if (c == '+' || c == '-') {
                while (topIsOperator(stack)) {
                    suffix.add(stack.removeLast());
                }
                stack.add(String.valueOf(c));
            } else if (Character.isLetter(c)) {
                StringBuilder variable = new StringBuilder();
                while (point < expression.length() && Character.isLetterOrDigit(expression.charAt(point))) {
                    variable.append(expression.charAt(point++));
                }
                point--;
                suffix.add(variable.toString());
            } else if (Character.isDigit(c)) {
                StringBuilder number = new StringBuilder();
                while (point < expression.length() && Character.isDigit(expression.charAt(point))) {
                    number.append(expression.charAt(point++));
                }
                point--;
                suffix.add(number.toString());
            } else {
                throw new IllegalArgumentException("Invalid character in expression: " + c);
            }
            point++;
        }
        while (!stack.isEmpty()) {
            suffix.add(stack.removeLast());
        }
        return suffix;
    }
    
    public Expression parse() {
        List<String> suffix = this.toSuffix();
        List<Expression> stack = new ArrayList<>();
        
        for (String token : suffix) {
            if (variables.containsKey(token)) {
                stack.add(new VariableExpression(token, variables));
            } else if (Character.isDigit(token.charAt(0))) {
                stack.add(new NumberExpression(Integer.parseInt(token)));
            } else if (token.equals("+")) {
                Expression right = stack.removeLast();
                Expression left = stack.removeLast();
                stack.add(new AddExpression(left, right));
            } else if (token.equals("-")) {
                Expression right = stack.removeLast();
                Expression left = stack.removeLast();
                stack.add(new SubtractExpression(left, right));
            } else if (token.equals("*")) {
                Expression right = stack.removeLast();
                Expression left = stack.removeLast();
                stack.add(new MultiplyExpression(left, right));
            } else if (token.equals("/")) {
                Expression right = stack.removeLast();
                Expression left = stack.removeLast();
                stack.add(new DivisionExpression(left, right));
            } else {
                throw new IllegalArgumentException("Unknown token: " + token);
            }
        }
        
        return stack.getLast();
    }
    
    private boolean topIsOperator(List<String> stack) {
        if (stack.isEmpty()) {
            return false;
        }
        return Set.of("+", "-", "*", "/").contains(stack.getLast());
    }
    
}