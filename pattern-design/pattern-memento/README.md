# 备忘录模式 (Memento Pattern)

## 介绍

备忘录模式是一种行为型设计模式，它允许我们在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，以便在需要时可以将对象恢复到原先保存的状态。在本项目中，我们通过实现文本编辑器的撤销/重做功能来展示备忘录模式的应用。

## 核心组件

1. **原发器（Originator）**
   ```java
   public class TextEditor {
       private StringBuilder buffer = new StringBuilder();
       
       public String getState() {
           return buffer.toString();
       }
       
       public void setState(String state) {
           buffer.delete(0, buffer.length());
           buffer.append(state);
       }
   }
   ```

2. **备忘录（Memento）**
    - 使用`String`类型存储文本编辑器的状态
    - 通过`backupLib`列表维护状态历史

3. **管理者（Caretaker）**
   ```java
   public class Invoker {
       private List<String> backupLib;
       private TextEditor receiver;
       private int index = 0;
       private boolean isRedo = false;
   }
   ```

## 实现细节

1. **状态管理**
    - 使用`backupLib`列表存储所有历史状态
    - 通过`index`指针追踪当前状态位置
    - 使用`isRedo`标志控制重做操作

2. **撤销/重做功能**
    - `undo()`: 恢复到上一个状态
    - `redo()`: 重做被撤销的操作
    - 支持连续撤销和重做

3. **状态恢复**
    - 通过`restore()`方法恢复指定状态
    - 保持对象封装性，不暴露内部实现

## 使用示例

```java
TextEditor editor = new TextEditor();
Command add = new AddCommand(editor, "Command pattern in text editor.\n");
Command copy = new CopyCommand(editor);
Command add1 = new AddCommand(editor, "---\n");
Command paste = new PasteCommand(editor);

Invoker invoker = new Invoker(editor);

// 执行一系列操作
invoker.invoke(add)
       .invoke(copy)
       .invoke(add1)
       .invoke(paste);

// 撤销操作
invoker.undo().undo();

// 重做操作
invoker.redo();
```

## 模式优势

1. **状态管理**: 可以方便地保存和恢复对象状态
2. **封装性**: 不暴露对象内部实现细节
3. **可维护性**: 状态管理逻辑与业务逻辑分离
4. **可扩展性**: 易于添加新的状态管理功能
5. **用户体验**: 支持撤销/重做等常用功能

## 参考

> [Memento Pattern](https://refactoringguru.cn/design-patterns/memento) 