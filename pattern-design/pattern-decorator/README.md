# 装饰器模式 (Decorator Pattern)

## 介绍

装饰器模式是一种结构型设计模式，它允许我们在不改变原有对象结构的情况下，动态地给对象添加新的功能。在本项目中，我们通过实现一个自定义注解 `@TimestampRequestBody`
来展示装饰器模式的应用。

## 核心功能

- 实现了一个自定义注解 `@TimestampRequestBody`，用于增强 `@RequestBody` 的功能
- 在请求体反序列化后，自动注入当前时间戳字段
- 无需客户端显式传递时间戳，简化了API调用

## 实现细节

1. **自定义注解**
   ```java
   @Target({ElementType.PARAMETER})
   @Retention(RetentionPolicy.RUNTIME)
   @Documented
   public @interface TimestampRequestBody {}
   ```

2. **参数解析器**
    - 继承 `HandlerMethodArgumentResolver` 接口
    - 装饰原有的 `RequestResponseBodyMethodProcessor`
    - 在解析完成后注入时间戳字段

## 使用示例

```java
@PostMapping("/api/data")
public ResponseEntity<?> handleRequest(@TimestampRequestBody Map<String, Object> requestBody) {
    // requestBody 中已自动包含 timestamp 字段
    return ResponseEntity.ok(requestBody);
}
```

## 模式优势

1. **功能增强**: 在不修改原有代码的情况下扩展功能
2. **灵活性**: 可以动态地添加或移除功能
3. **符合开闭原则**: 对扩展开放，对修改关闭
4. **代码复用**: 通过组合方式复用现有功能

## 参考

> [Decorator Pattern](https://refactoringguru.cn/design-patterns/decorator)
> [【设计模式实战】装饰模式](https://www.bilibili.com/video/BV1bgPBesENK)