package com.helltractor.demo.reentrantlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;


public class ReentrantReadWriteLockTest {
    
    @Test
    public void testReentrantReadWriteLock() {
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        ReentrantReadWriteLockSample sample = new ReentrantReadWriteLockSample();
        for (int i = 0; i < 20; i++) {
            executorService.execute(new ReentrantReadWriteLockSample.ReentrantReadWriteLockThread());
            sample.cache.get(0);
        }
        executorService.shutdown();
    }
    
}