package com.helltractor.demo;

public class Printer {

    private int count = 0;
    private final int maxCount = 200;
    private final Object lock = new Object();
    private boolean isOdd = false;

    public static void main(String[] args) {
        Printer printer = new Printer();

        Thread oddThread = new Thread(() -> {
            printer.print("Thread Odd", true);
        });
        Thread evenThread = new Thread(() -> {
            printer.print("Thread Even", false);
        });

        oddThread.start();
        evenThread.start();
    }

    public void print(String name, boolean isOdd) {
        synchronized (lock) {
            while (count < maxCount) {
                if (this.isOdd != isOdd) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println(name + ": " + count);
                count++;
                this.isOdd = !this.isOdd;
                lock.notifyAll();
            }
        }
    }
}
