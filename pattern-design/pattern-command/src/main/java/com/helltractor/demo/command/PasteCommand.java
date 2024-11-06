package com.helltractor.demo.command;

import com.helltractor.demo.editor.TextEditor;

public class PasteCommand implements Command {

	private TextEditor receiver;
	
	public PasteCommand(TextEditor receiver) {
		this.receiver = receiver;
	}

	@Override
	public void execute() {
		receiver.paste();
	}
	
	@Override
	public void undo() {}
}
