package com.pattern.command.command;

import com.pattern.command.editor.TextEditor;

public class CopyCommand implements Command {

	private TextEditor receiver;

	public CopyCommand(TextEditor receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.copy();
	}
	
	@Override
	public void undo() {
	}
}
