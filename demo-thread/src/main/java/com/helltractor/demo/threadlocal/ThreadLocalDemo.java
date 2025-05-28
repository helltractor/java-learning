package com.helltractor.demo.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo {

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(3);
        String[] users = new String[]{"Bob", "Alice", "Tim", "Mike", "Lily", "Jack", "Bush"};
        for (String user : users) {
            es.submit(new ThreadLocalTask(user));
        }
        es.awaitTermination(3, TimeUnit.SECONDS);
        es.shutdown();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
    }

}
