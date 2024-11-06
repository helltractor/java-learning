package com.helltractor.demo.invoker;

import com.helltractor.demo.command.Command;

import java.util.ArrayList;
import java.util.List;

public class Invoker{
    
    private List<Command> historyCommands;
    
    public Invoker(){
        this.historyCommands = new ArrayList<>();
    }
    
    public Invoker invoke(Command command){
        historyCommands.add(command);
        command.execute();
        return this;
    }
    
    public void undo(){
        if (!historyCommands.isEmpty()) {
            Command command = historyCommands.remove(historyCommands.size() - 1);
            if (command != null) {
                command.undo();
            }
        }
    }
}
