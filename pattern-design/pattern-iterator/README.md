# 迭代器模式 (Iterator Pattern)

## 介绍

迭代器模式是一种行为型设计模式，它提供一种方法顺序访问一个聚合对象中的各个元素，而又不暴露其内部的表示。在本项目中，我们通过实现文件读取的多种迭代方式来展示迭代器模式的应用。

## 核心组件

1. **基础迭代器**
   ```java
   public class UserFile implements Iterable<User> {
       private final File file;
       
       @Override
       public Iterator<User> iterator() {
           return new UserFileIterator();
       }
   }
   ```

2. **批量迭代器**
   ```java
   public class UserFileBatch implements Iterable<List<User>> {
       private final File file;
       private int batchSize = 10;
       
       @Override
       public Iterator<List<User>> iterator() {
           return new UserFileBatchIterator();
       }
   }
   ```

3. **延迟加载迭代器**
   ```java
   public class UserFileBatchRead implements Iterable<User> {
       private final File file;
       private int batchSize = 10;
       
       @Override
       public Iterator<User> iterator() {
           return new UserFileReadBatchIterator();
       }
   }
   ```

## 实现细节

1. **基础文件迭代**
   - 一次性读取所有数据到内存
   - 支持单条记录遍历
   - 使用Java 8 Stream API处理数据

2. **批量文件迭代**
   - 按批次读取数据
   - 每次返回一批用户数据
   - 支持自定义批次大小

3. **延迟加载迭代**
   - 按需读取数据
   - 维护内部缓冲区
   - 自动处理文件关闭

## 使用示例

```java
// 基础迭代
File file = new File("user.txt");
UserFile userFile = new UserFile(file);
for (User user : userFile) {
    System.out.println(user.toString());
}

// 批量迭代
UserFileBatch userFileBatch = new UserFileBatch(file);
for (List<User> batch : userFileBatch) {
    for (User user : batch) {
        System.out.println(user.toString());
    }
}

// 延迟加载迭代
UserFileBatchRead userFileBatchRead = new UserFileBatchRead(file);
for (User user : userFileBatchRead) {
    System.out.println(user.toString());
}
```

## 模式优势

1. **封装性**: 隐藏集合的内部实现
2. **统一接口**: 提供统一的遍历方式
3. **灵活性**: 支持多种遍历策略
4. **内存优化**: 支持延迟加载和批量处理
5. **可扩展性**: 易于添加新的迭代方式

## 参考

> [Iterator Pattern](https://refactoringguru.cn/design-patterns/iterator) 
> [【设计模式实战】迭代器模式]（https://www.bilibili.com/video/BV1XhNSeYEVy）