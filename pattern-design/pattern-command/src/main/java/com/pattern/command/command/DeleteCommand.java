package com.pattern.command.command;

import com.pattern.command.editor.TextEditor;

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
