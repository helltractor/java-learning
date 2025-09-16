package com.helltractor.demo.reentrantlock;

import java.util.Random;

public class ReentrantReadWriteLockSample {

    public static UnboundedCache<Integer, Integer> cache = new UnboundedCache<>();

    public static class ReentrantReadWriteLockThread implements Runnable {

        @Override
        public void run() {
            Random random = new Random();
            for (int i = 0; i < 3; i++) {
                cache.put(i, random.nextInt(100));
            }
        }
    }

}
