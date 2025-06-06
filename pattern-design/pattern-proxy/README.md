# 代理模式 (Proxy Pattern)

## 介绍

代理模式是一种结构型设计模式，它允许我们提供一个代理对象来控制对其他对象的访问。在本项目中，我们通过实现数据库连接池来展示代理模式的应用，主要实现了两种代理：
1. 延迟加载代理（Lazy Proxy）
2. 连接池代理（Pooled Proxy）

## 核心组件

1. **抽象代理基类**
   ```java
   public abstract class AbstractConnectionProxy implements Connection {
       protected abstract Connection getRealConnection();
       // 代理所有Connection接口方法
   }
   ```

2. **延迟加载代理**
   - `LazyConnectionProxy`: 实现延迟加载数据库连接
   - `LazyDataSource`: 提供延迟加载的数据源

3. **连接池代理**
   - `PooledConnectionProxy`: 实现连接池管理
   - `PooledDataSource`: 提供连接池数据源

4. **组合代理**
   - `LazyPooledConnectionProxy`: 结合延迟加载和连接池功能
   - `LazyPooledDataSource`: 提供延迟加载的连接池数据源

## 实现细节

1. **延迟加载代理**
   - 只有在真正需要时才创建数据库连接
   - 使用Supplier函数式接口延迟创建连接
   - 减少不必要的资源消耗

2. **连接池代理**
   - 维护一个空闲连接队列
   - 重用已创建的连接
   - 提高性能和资源利用率

3. **组合代理**
   - 结合延迟加载和连接池的优点
   - 既实现延迟加载，又支持连接重用
   - 提供更灵活的资源管理

## 使用示例

```java
// 使用延迟加载数据源
DataSource lazyDataSource = new LazyDataSource(jdbcUrl, username, password);
try (Connection conn = lazyDataSource.getConnection()) {
    // 只有在实际执行SQL时才创建连接
    try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM students")) {
        // 执行查询
    }
}

// 使用连接池数据源
DataSource pooledDataSource = new PooledDataSource(jdbcUrl, username, password);
try (Connection conn = pooledDataSource.getConnection()) {
    // 获取到的是池中的连接
}
```

## 模式优势

1. **资源优化**: 通过延迟加载和连接池提高资源利用率
2. **性能提升**: 减少连接创建和销毁的开销
3. **透明性**: 客户端无需关心连接管理的细节
4. **灵活性**: 可以方便地添加新的代理功能
5. **可维护性**: 代理逻辑与业务逻辑分离

## 参考

> [Proxy Pattern](https://refactoringguru.cn/design-patterns/proxy) 