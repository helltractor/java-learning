package com.helltractor.demo.command;

import com.helltractor.demo.editor.TextEditor;

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
