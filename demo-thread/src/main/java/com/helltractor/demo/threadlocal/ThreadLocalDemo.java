package com.helltractor.demo.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo {
    
    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(3);
        String[] users = new String[]{"Bob", "Alice", "Tim", "Mike", "Lily", "Jack", "Bush"};
        for (String user : users) {
            es.submit(new TaskThread(user));
        }
        es.awaitTermination(3, TimeUnit.SECONDS);
        es.shutdown();
        
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        inheritableThreadLocal.set("Hello from parent thread!");
        
        Thread childThread = new Thread(() -> {
            System.out.println("Child thread: " + inheritableThreadLocal.get());
            inheritableThreadLocal.set("Hello from child thread!");
            System.out.println("Child thread after set: " + inheritableThreadLocal.get());
        });
        
        childThread.start();
        childThread.join();
        
        System.out.println("Parent thread after child thread: " + inheritableThreadLocal.get());
        inheritableThreadLocal.remove();
    }
    
}
