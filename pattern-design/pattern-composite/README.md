# 组合模式 (Composite Pattern)

## 介绍

组合模式是一种结构型设计模式，它允许你将对象组合成树形结构来表示"部分-整体"的层次结构。组合模式使得客户端对单个对象和组合对象的使用具有一致性。

在本项目中，我们通过一个表达式计算器的实现来展示组合模式的应用。该实现支持：

- 基本数学运算（加、减、乘、除）
- 数字表达式
- 变量表达式
- 复杂表达式的解析和计算

## 核心组件

1. **Expression接口**
    - 定义了所有表达式对象的通用接口
    - 包含`getValue()`方法用于计算表达式的值

2. **具体表达式类**
    - `NumberExpression`: 表示数字常量
    - `VariableExpression`: 表示变量
    - `AddExpression`: 加法运算
    - `SubtractExpression`: 减法运算
    - `MultiplyExpression`: 乘法运算
    - `DivisionExpression`: 除法运算

3. **表达式解析器**
    - `ExpressionParser`: 负责将字符串表达式解析为表达式对象树

## 使用示例

```java
// 解析简单表达式
String expressionString = "(1+2)*(3+4)/2";
ExpressionParser parser = new ExpressionParser(expressionString);
Expression expression = parser.parse();
System.out.println(expression.getValue());

// 使用变量的表达式
Map<String, Integer> variables = Map.of("a", 10, "b", 20);
ExpressionParser parserWithVars = new ExpressionParser("a + b * 10 / 9", variables);
Expression expressionWithVars = parserWithVars.parse();
System.out.println(expressionWithVars.getValue());
```

## 模式优势

1. **统一处理**: 客户端可以用统一的方式处理单个对象和组合对象
2. **灵活性**: 可以轻松添加新的表达式类型
3. **可扩展性**: 支持复杂的表达式组合
4. **维护性**: 代码结构清晰，易于维护和扩展

## 参考

> [Composite Pattern](https://refactoringguru.cn/design-patterns/composite)
> [【设计模式实战】组合模式](https://www.bilibili.com/video/BV1h8A2eSEfy/)