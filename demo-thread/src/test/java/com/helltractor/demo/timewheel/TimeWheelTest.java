package com.helltractor.demo.timewheel;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;


class TimerWheelTest {
    
    @Test
    void testThreadSafe() throws InterruptedException {
        TimeWheel timerWheel = new TimeWheel();
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    timerWheel.addDelayTask(() -> {
                        System.out.println(count.incrementAndGet());
                        countDownLatch.countDown();
                    }, 100);
                }
                System.out.println("添加结束");
            }).start();
        }
        countDownLatch.await();
    }
    
}