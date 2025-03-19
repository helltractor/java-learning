package com.helltractor.demo;

import com.helltractor.demo.command.AddCommand;
import com.helltractor.demo.command.Command;
import com.helltractor.demo.command.CopyCommand;
import com.helltractor.demo.command.PasteCommand;
import com.helltractor.demo.editor.TextEditor;
import com.helltractor.demo.invoker.Invoker;

public class CommandDemo {
    
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Command add = new AddCommand(editor, "Command pattern in text editor.\n");
        Command copy = new CopyCommand(editor);
        Command add1 = new AddCommand(editor, "---\n");
        Command paste = new PasteCommand(editor);
        Invoker invoker = new Invoker();
        invoker.invoke(add).invoke(copy).invoke(add1).invoke(paste);
        System.out.println(editor.getState());
        invoker.undo();
        System.out.println(editor.getState());
    }
    
}