package com.helltractor.demo.reentrantlock;

class MyThread extends Thread {
    private Task task;
    
    public MyThread(String name, Task task) {
        super(name);
        this.task = task;
    }
    
    @Override
    public void run() {
        task.execute();
    }
    
}