package com.helltractor.demo.reentrantlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;


public class ReentrantReadWriteLockTest {
    
    @Test
    public void testReentrantReadWriteLock() {
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo();
        for (int i = 0; i < 20; i++) {
            executorService.execute(demo.new MyThread());
            demo.cache.get(0);
        }
        executorService.shutdown();
    }
    
}