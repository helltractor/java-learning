package com.helltractor.demo.timewheel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A multi-producer single-consumer task queue for scheduling tasks in a time wheel.
 */
public class MpscTaskQueue {
    
    private final AtomicReference<TimeWheel.DelayTask> head = new AtomicReference<>(null);
    
    public void pushTask(TimeWheel.DelayTask delayTask) {
        while (true) {
            TimeWheel.DelayTask currentHead = head.get();
            delayTask.next = currentHead;
            if (head.compareAndSet(currentHead, delayTask)) {
                return;
            }
        }
    }
    
    public List<Runnable> removeAndReturnTasks(long tickTime) {
        List<Runnable> result = new ArrayList<>();
        TimeWheel.DelayTask prev = null;
        TimeWheel.DelayTask current = head.get();
        
        while (current != null) {
            if (current.deadline > tickTime) {
                prev = current;
                current = current.next;
                continue;
            }
            TimeWheel.DelayTask next = current.next;
            if (prev != null) {
                prev.next = next;
                current.next = null;
                current = next;
                continue;
            }
            if (head.compareAndSet(current, next)) {
                result.add(current.task);
                current.next = null;
                current = next;
                continue;
            }
            current = head.get();
        }
        
        return result;
    }
    
}