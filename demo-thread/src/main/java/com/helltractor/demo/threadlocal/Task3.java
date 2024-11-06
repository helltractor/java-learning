package com.helltractor.demo.threadlocal;

class Task3 {
    public void process() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        System.out.printf("[%s] work of %s has done.\n", Thread.currentThread().getName(),
                UserContext.getCurrentUser());
    }
}
