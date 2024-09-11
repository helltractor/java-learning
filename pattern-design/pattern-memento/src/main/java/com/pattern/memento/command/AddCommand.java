package com.pattern.memento.command;

import com.pattern.memento.editor.TextEditor;

public class AddCommand implements Command {
    
    private TextEditor receiver;
    private String text;
    
    public AddCommand(TextEditor receiver, String text){
        this.receiver = receiver;
        this.text = text;
    }
    
    @Override
    public void execute(){
        execute(text);
    }
    
    private void execute(String text) {
        receiver.add(text);
    }
}
