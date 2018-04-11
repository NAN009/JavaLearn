package com.zenan.zhang.learning.reading.notes.jvm;

public class SuperClass {
    static {
        System.out.println("SuperClass init!");
    }
    public SuperClass(){
        System.out.println("SuperClass B");
    }
    public static int value = 123;
}
