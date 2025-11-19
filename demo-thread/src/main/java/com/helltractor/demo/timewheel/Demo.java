package com.helltractor.demo.timewheel;

public class Demo {
    
    public static void main(String[] args) {
        TimeWheel timeWheel = new TimeWheel();
        for (int i = 0; i < 123; i++) {
            final int index = i;
            timeWheel.addDelayTask(() -> {
                System.out.println(index);
            }, 1000L * i);
        }
    }
    
}