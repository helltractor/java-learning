package com.helltractor.demo.calc;

import java.util.Map;

public class CalculateDemo {
    
    public static void main(String[] args) {
        String expressionString = "(1+2)*(3+4)/2";
        ExpressionParser expressionParser = new ExpressionParser(expressionString);
        Expression parse = expressionParser.parse();
        System.out.println("Parsed Expression: " + parse.getValue()); // Output: Parsed Expression: (1 + 2) * (3 + 4) / 2
        
        int a = 10;
        int b = 20;
        ExpressionParser expressionParserWithVars = new ExpressionParser("a + b * 10 / 9", Map.of("a", a, "b", b));
        Expression parseWithVars = expressionParserWithVars.parse();
        System.out.println("Parsed Expression with Variables: " + parseWithVars.getValue()); // Output: Parsed Expression with Variables: 30
    }
    
}