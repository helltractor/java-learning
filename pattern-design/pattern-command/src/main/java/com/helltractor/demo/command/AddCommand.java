package com.helltractor.demo.command;

import com.helltractor.demo.editor.TextEditor;

public class AddCommand implements Command {
    
    private TextEditor receiver;
    private String text;
    private String deletedText;
    
    public AddCommand(TextEditor receiver, String text) {
        this.receiver = receiver;
        this.text = text;
    }
    
    @Override
    public void execute() {
        execute(text);
    }
    
    private void execute(String text) {
        deletedText = text;
        receiver.add(text);
    }
    
    @Override
    public void undo() {
        int length = receiver.getLength();
        receiver.delete(length - deletedText.length(), length);
    }
    
}