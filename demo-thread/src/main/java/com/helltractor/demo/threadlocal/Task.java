package com.helltractor.demo.threadlocal;

class Task implements Runnable {

    final String username;

    public Task(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        try (var ctx = new UserContext(this.username)) {
            new Task1().process();
            new Task2().process();
            new Task3().process();
        }
    }

}
