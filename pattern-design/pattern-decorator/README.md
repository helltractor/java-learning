## Decorator Pattern

## 介绍

实现一个自定义注解 `@TimestampRequestBody`，用于拦截并增强 `@RequestBody` 注解解析过程，
在反序列化完成后，自动为请求体对象注入当前时间戳字段，无需客户端显式传递。

## 参考

> [Decorator Pattern](https://refactoringguru.cn/design-patterns/decorator)
> [装饰模式](https://www.bilibili.com/video/BV1bgPBesENK)