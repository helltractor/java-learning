package com.helltractor.demo.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    private static void getReentrantLockContext(ReentrantLock lock) {
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println(lock.toString());

                // 查询当前线程 hold 住此锁的次数
                System.out.println("\t holdCount: " + lock.getHoldCount());

                // 查询正等待获取此锁的线程数
                System.out.println("\t queuedLength: " + lock.getQueueLength());

                // 是否为公平锁
                System.out.println("\t isFair: " + lock.isFair());

                // 是否被锁住
                System.out.println("\t isLocked: " + lock.isLocked());

                // 是否被当前线程持有锁
                System.out.println("\t isHeldByCurrentThread: " + lock.isHeldByCurrentThread());

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static class LockTask implements Task {

        private ReentrantLock lock = new ReentrantLock();

        public void execute() {
            lock.lock();
            getReentrantLockContext(lock);
        }
    }

    public static class TryLockTask implements Task {

        private ReentrantLock lock = new ReentrantLock();

        public void execute() {
            if (lock.tryLock()) {
                getReentrantLockContext(lock);
            } else {
                System.out.println(Thread.currentThread().getName() + " get lock failed.");
            }
        }
    }

    public static class TryLockTaskTwo implements Task {

        private ReentrantLock lock = new ReentrantLock();

        public void execute() {
            try {
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    getReentrantLockContext(lock);
                } else {
                    System.out.println(Thread.currentThread().getName() + " get lock failed.");
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " get lock timeout.");
                e.printStackTrace();
            }
        }
    }

    public static class LockInterruptiblyTask implements Task {

        private ReentrantLock lock = new ReentrantLock();

        public void execute() {
            try {
                lock.lockInterruptibly();
                getReentrantLockContext(lock);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "interrupted.");
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}
