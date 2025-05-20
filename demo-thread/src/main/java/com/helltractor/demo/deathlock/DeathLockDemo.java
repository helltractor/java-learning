package com.helltractor.demo.deathlock;

public class DeathLockDemo {

    private static Object resource1 = new Object(); //资源 1
    private static Object resource2 = new Object(); //资源 2

    private Thread t1 = createThread("Thread-1", resource1, resource2);
    private Thread t2 = createThread("Thread-2", resource2, resource1);
    private Thread t3 = createThread("Thread-3", resource1, resource2);

    public static void main(String[] args) {
        DeathLockDemo demo = new DeathLockDemo();
//        demo.deathLock();
        demo.breakDeathLock();
    }

    public void deathLock() {
        t1.start();
        t2.start();
    }

    public void breakDeathLock() {
        t1.start();
        t3.start();
    }

    private Thread createThread(String name, Object... resources) {
        return new Thread(() -> {
            synchronized (resources[0]) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resources[1]) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, name);
    }

}
