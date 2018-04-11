package com.zenan.zhang.learning.reading.notes.jvm;

public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }

    public SubClass() {
        System.out.println("SubClass B");
    }
}
