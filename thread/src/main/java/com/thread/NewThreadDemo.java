package com.thread;

public class NewThreadDemo {
    
    public static void main(String[] args) throws InterruptedException {
        // Using a class that extends Thread
        Thread t1 = new MyThread();
        
        // Using a class that implements Runnable
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread-1 is running");
            }
        });
        
        // Lambda expression
        Thread t3 = new Thread(() -> {
            System.out.println("Thread-3 is running");
        });
        
        // Set Priority of a Thread, 1 to 10, 1 is the lowest and 10 is the highest, default is 5
        System.out.println("Current priority of t1: " + t1.getPriority());
        t1.setPriority(1);
        System.out.println("Current priority of t1: " + t1.getPriority());
        t2.setPriority(10);
        
        t1.start();
        Thread.sleep(10);
        t2.start();
        t3.start();
    }
    
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread-2 is running");
        }
    }
}
