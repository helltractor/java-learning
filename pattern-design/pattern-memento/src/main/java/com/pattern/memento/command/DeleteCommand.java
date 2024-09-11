package com.pattern.memento.command;

import com.pattern.memento.editor.TextEditor;

public class DeleteCommand implements Command {

    private TextEditor receiver;
    
    public DeleteCommand(TextEditor receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.delete();
    }
}
