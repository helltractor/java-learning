package com.helltractor.demo.invoker;

import com.helltractor.demo.command.Command;
import com.helltractor.demo.command.CopyCommand;
import com.helltractor.demo.editor.TextEditor;

import java.util.ArrayList;
import java.util.List;

public class Invoker {

    private List<String> backupLib;
    private TextEditor receiver;

    private int index = 0;

    private boolean isRedo = false;

    public Invoker(TextEditor receiver) {
        this.backupLib = new ArrayList<>(List.of(new String()));
        this.receiver = receiver;
    }

    public Invoker invoke(Command c) {
        c.execute();
        if (!(c instanceof CopyCommand)) {//copy命令不备份数据
            if (index > 0 && index < backupLib.size() - 1) {//覆盖版本范围，从1到最新版本-1
                index++;
                isRedo = false;
                backupLib.set(index, receiver.getState());
            } else {
                backupLib.add(receiver.getState());
                index++;
            }
        }

        return this;
    }

    public Invoker undo() {
        if (index > 0) {
            index--;
            isRedo = true;
            restore(index);
        }
        return this;
    }

    public Invoker redo() {
        if (isRedo && index < backupLib.size() - 1) {
            index++;
            restore(index);
        }
        return this;
    }

    public void restore(int index) {
        String restore = backupLib.get(index);
        receiver.setState(restore);
    }

}
