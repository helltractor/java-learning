package com.pattern.memento.command;

import com.pattern.memento.editor.TextEditor;

public class PasteCommand implements Command {

	private TextEditor receiver;
	
	public PasteCommand(TextEditor receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.paste();
	}
}
