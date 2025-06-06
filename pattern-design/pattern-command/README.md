# 命令模式 (Command Pattern)

## 介绍

命令模式是一种行为型设计模式，它将请求封装为一个对象，从而使你可以用不同的请求对客户进行参数化，对请求排队或记录请求日志，以及支持可撤销的操作。在本项目中，我们通过实现一个简单的文本编辑器来展示命令模式的应用。

## 核心组件

1. **Command接口**
   ```java
   public interface Command {
       void execute();
       void undo();
   }
   ```

2. **具体命令类**
   - `AddCommand`: 添加文本命令
   - `DeleteCommand`: 删除文本命令
   - `CopyCommand`: 复制文本命令
   - `PasteCommand`: 粘贴文本命令

3. **接收者**
   - `TextEditor`: 实际执行命令的类，包含文本编辑的核心功能

4. **调用者**
   - `Invoker`: 负责调用命令，维护命令历史，支持撤销操作

## 实现细节

1. **命令封装**
   - 每个命令都封装了具体的操作和撤销操作
   - 命令对象持有对接收者（TextEditor）的引用

2. **命令历史**
   - Invoker维护命令历史列表
   - 支持撤销操作，可以按顺序撤销已执行的命令

3. **文本编辑器功能**
   - 支持基本的文本操作：添加、删除、复制、粘贴
   - 使用系统剪贴板实现复制粘贴功能

## 使用示例

```java
TextEditor editor = new TextEditor();
Command add = new AddCommand(editor, "Command pattern in text editor.\n");
Command copy = new CopyCommand(editor);
Command add1 = new AddCommand(editor, "---\n");
Command paste = new PasteCommand(editor);

Invoker invoker = new Invoker();
invoker.invoke(add)
       .invoke(copy)
       .invoke(add1)
       .invoke(paste);

// 撤销操作
invoker.undo();
```

## 模式优势

1. **解耦**: 将请求发送者和接收者解耦
2. **可扩展**: 易于添加新的命令，无需修改现有代码
3. **可撤销**: 支持操作的撤销和重做
4. **命令队列**: 支持命令的排队执行
5. **日志记录**: 可以记录命令执行历史

## 参考

> [Command Pattern](https://refactoringguru.cn/design-patterns/command)