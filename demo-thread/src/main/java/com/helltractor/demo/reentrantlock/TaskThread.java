package com.helltractor.demo.reentrantlock;

class TaskThread extends Thread {
    
    private final Task task;
    
    public TaskThread(String name, Task task) {
        super(name);
        this.task = task;
    }
    
    @Override
    public void run() {
        task.execute();
    }
    
}
