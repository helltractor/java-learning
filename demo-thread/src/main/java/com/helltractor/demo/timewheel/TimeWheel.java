package com.helltractor.demo.timewheel;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * A simple time wheel implementation for scheduling delayed tasks.
 * Use MPSC task queues for each slot to allow concurrent task additions.
 */
public class TimeWheel {
    
    private final AtomicBoolean started;
    private final CountDownLatch startTimeLatch;
    private final Ticker ticker;
    private final ExecutorService executor;
    private final MpscTaskQueue[] wheel;
    
    // avoid multi-thread read/write issues
    private volatile long startTime;
    
    public TimeWheel() {
        this.started = new AtomicBoolean(false);
        this.startTimeLatch = new CountDownLatch(1);
        this.ticker = new Ticker();
        this.executor = Executors.newFixedThreadPool(8);
        this.wheel = new MpscTaskQueue[10];
        for (int i = 0; i < wheel.length; i++) {
            wheel[i] = new MpscTaskQueue();
        }
    }
    
    public void start() {
        if (started.compareAndSet(false, true)) {
            ticker.start();
        }
        try {
            startTimeLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void stop() {
        if (started.compareAndSet(true, false)) {
            LockSupport.unpark(ticker);
        }
    }
    
    public void addDelayTask(Runnable task, long delayMillis) {
        start();
        DelayTask delayTask = new DelayTask(task, delayMillis);
        int index = Math.toIntExact((delayTask.deadline - startTime) / 100 % wheel.length);
        MpscTaskQueue slot = wheel[index];
        slot.pushTask(delayTask);
    }
    
    static class DelayTask {
        final Runnable task;
        final long deadline;
        DelayTask next;
        
        public DelayTask(Runnable task, long delayTime) {
            this.task = task;
            this.deadline = System.currentTimeMillis() + delayTime;
        }
    }
    
    class Ticker extends Thread {
        
        int tickCount = 0;
        
        @Override
        public void run() {
            startTime = System.currentTimeMillis();
            startTimeLatch.countDown();
            while (started.get()) {
                long tickTime = startTime + (tickCount + 1) * 100L;
                while (System.currentTimeMillis() <= tickTime) {
                    LockSupport.parkUntil(tickTime);
                    // check if stopped
                    if (!started.get()) {
                        return;
                    }
                }
                int index = tickCount % wheel.length;
                MpscTaskQueue queue = wheel[index];
                List<Runnable> tasks = queue.removeAndReturnTasks(tickTime);
                tasks.forEach(executor::execute);
                tickCount++;
            }
            
        }
    }
    
}