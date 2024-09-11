package com.pattern.command;

import com.pattern.command.command.AddCommand;
import com.pattern.command.command.Command;
import com.pattern.command.command.CopyCommand;
import com.pattern.command.command.PasteCommand;
import com.pattern.command.editor.TextEditor;
import com.pattern.command.invoker.Invoker;

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
