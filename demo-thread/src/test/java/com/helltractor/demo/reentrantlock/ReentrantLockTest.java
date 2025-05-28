package com.helltractor.demo.reentrantlock;

import com.helltractor.demo.reentrantlock.ReentrantLockDemo.LockInterruptiblyTask;
import com.helltractor.demo.reentrantlock.ReentrantLockDemo.LockTask;
import com.helltractor.demo.reentrantlock.ReentrantLockDemo.TryLockTask;
import com.helltractor.demo.reentrantlock.ReentrantLockDemo.TryLockTaskTwo;
import org.junit.jupiter.api.Test;

public class ReentrantLockTest {
    
    @Test
    public void testReentrantLock() {
        LockTask lockTask = new LockTask();
        MyThread thread1 = new MyThread("Thread 1", lockTask);
        MyThread thread2 = new MyThread("Thread 2", lockTask);
        MyThread thread3 = new MyThread("Thread 3", lockTask);
        
        thread1.start();
        thread2.start();
        thread3.start();
    }
    
    @Test
    public void testTryLock() {
        TryLockTask tryLockTask = new TryLockTask();
        MyThread thread1 = new MyThread("Thread 1", tryLockTask);
        MyThread thread2 = new MyThread("Thread 2", tryLockTask);
        MyThread thread3 = new MyThread("Thread 3", tryLockTask);
        
        thread1.start();
        thread2.start();
        thread3.start();
    }
    
    @Test
    public void testTryLockWithTimeout() {
        TryLockTaskTwo tryLockTask = new TryLockTaskTwo();
        MyThread thread1 = new MyThread("Thread 1", tryLockTask);
        MyThread thread2 = new MyThread("Thread 2", tryLockTask);
        MyThread thread3 = new MyThread("Thread 3", tryLockTask);
        
        thread1.start();
        thread2.start();
        thread3.start();
    }
    
    @Test
    public void testTryLockWithTimeoutAndNanos() {
        LockInterruptiblyTask lockInterruptiblyTask = new LockInterruptiblyTask();
        MyThread thread1 = new MyThread("Thread 1", lockInterruptiblyTask);
        MyThread thread2 = new MyThread("Thread 2", lockInterruptiblyTask);
        MyThread thread3 = new MyThread("Thread 3", lockInterruptiblyTask);
        
        thread1.start();
        thread2.start();
        thread3.start();
    }
    
}