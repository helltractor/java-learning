package com.helltractor.demo.command;

import com.helltractor.demo.editor.TextEditor;

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