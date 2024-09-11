package com.pattern.memento;

import com.pattern.memento.command.AddCommand;
import com.pattern.memento.command.Command;
import com.pattern.memento.command.CopyCommand;
import com.pattern.memento.command.PasteCommand;
import com.pattern.memento.editor.TextEditor;
import com.pattern.memento.invoker.Invoker;

public class MementoDemo {

	public static void main(String[] args) {
		TextEditor editor = new TextEditor();
		Command add = new AddCommand(editor, "Command pattern in text editor.\n");
		Command copy = new CopyCommand(editor);
		Command add1 = new AddCommand(editor, "---\n");
		Command paste = new PasteCommand(editor);
		Invoker invoker = new Invoker(editor);
		
		invoker.invoke(add).invoke(copy).invoke(add1).invoke(paste);
		System.out.println(editor.getState());
		
		invoker.undo().undo().invoke(add1).redo();
		System.out.println(editor.getState());
		
		invoker.undo().redo().invoke(add);
		System.out.println(editor.getState());
	}
}
