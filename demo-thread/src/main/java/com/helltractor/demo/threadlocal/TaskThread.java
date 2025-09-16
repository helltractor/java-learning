package com.helltractor.demo.threadlocal;

class TaskThread implements Runnable {

    private final String username;

    public TaskThread(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        try (var ctx = new UserContext(this.username)) {
            new TaskOne().process();
            new TaskTwo().process();
            new TaskThree().process();
        }
    }

    static class TaskOne {
        public void process() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("[%s] work of %s has done.\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
        }
    }

    static class TaskTwo {
        public void process() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("[%s] check user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
        }
    }

    static class TaskThree {
        public void process() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("[%s] work of %s has done.\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
        }
    }

}
