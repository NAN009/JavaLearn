package com.zenan.zhang.learning.reading.notes.jvm;

public class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }

    public static final String HELLO_WORLD = "hello world";
}
