package com.helltractor.demo.command;

import com.helltractor.demo.editor.TextEditor;

public class DeleteCommand implements Command {

    private TextEditor receiver;
    private String text;
    
    public DeleteCommand(TextEditor receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        text = receiver.getCursorChar();
        receiver.delete();
    }

    @Override
    public void undo() {
        receiver.add(text);
    }
}
