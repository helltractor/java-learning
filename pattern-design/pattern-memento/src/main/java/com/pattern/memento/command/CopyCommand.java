package com.pattern.memento.command;

import com.pattern.memento.editor.TextEditor;

public class CopyCommand implements Command {

	private TextEditor receiver;

	public CopyCommand(TextEditor receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.copy();
	}
}
