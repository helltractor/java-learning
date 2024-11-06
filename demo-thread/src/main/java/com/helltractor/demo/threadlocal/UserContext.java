package com.helltractor.demo.threadlocal;

class UserContext implements AutoCloseable {
    private static final ThreadLocal<String> userThreadLocal = new ThreadLocal<>();
    
    public UserContext(String name) {
        userThreadLocal.set(name);
        System.out.printf("[%s] init user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
    }
    
    public static String getCurrentUser() {
        return userThreadLocal.get();
    }
    
    @Override
    public void close() {
        System.out.printf("[%s] cleanup for user %s...\n", Thread.currentThread().getName(),
                UserContext.getCurrentUser());
        userThreadLocal.remove();
    }
}
