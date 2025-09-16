package com.helltractor.demo.reentrantlock;

import com.helltractor.demo.reentrantlock.ReentrantLockSample.LockInterruptiblyTask;
import com.helltractor.demo.reentrantlock.ReentrantLockSample.LockTask;
import com.helltractor.demo.reentrantlock.ReentrantLockSample.TryLockTask;
import com.helltractor.demo.reentrantlock.ReentrantLockSample.TryLockTaskTwo;
import org.junit.jupiter.api.Test;

public class ReentrantLockTest {
    
    @Test
    public void testReentrantLock() {
        LockTask lockTask = new LockTask();
        TaskThread thread1 = new TaskThread("Thread 1", lockTask);
        TaskThread thread2 = new TaskThread("Thread 2", lockTask);
        TaskThread thread3 = new TaskThread("Thread 3", lockTask);
        
        thread1.start();
        thread2.start();
        thread3.start();
    }
    
    @Test
    public void testTryLock() {
        TryLockTask tryLockTask = new TryLockTask();
        TaskThread thread1 = new TaskThread("Thread 1", tryLockTask);
        TaskThread thread2 = new TaskThread("Thread 2", tryLockTask);
        TaskThread thread3 = new TaskThread("Thread 3", tryLockTask);
        
        thread1.start();
        thread2.start();
        thread3.start();
    }
    
    @Test
    public void testTryLockWithTimeout() {
        TryLockTaskTwo tryLockTask = new TryLockTaskTwo();
        TaskThread thread1 = new TaskThread("Thread 1", tryLockTask);
        TaskThread thread2 = new TaskThread("Thread 2", tryLockTask);
        TaskThread thread3 = new TaskThread("Thread 3", tryLockTask);
        
        thread1.start();
        thread2.start();
        thread3.start();
    }
    
    @Test
    public void testTryLockWithTimeoutAndNanos() {
        LockInterruptiblyTask lockInterruptiblyTask = new LockInterruptiblyTask();
        TaskThread thread1 = new TaskThread("Thread 1", lockInterruptiblyTask);
        TaskThread thread2 = new TaskThread("Thread 2", lockInterruptiblyTask);
        TaskThread thread3 = new TaskThread("Thread 3", lockInterruptiblyTask);
        
        thread1.start();
        thread2.start();
        thread3.start();
    }
    
}