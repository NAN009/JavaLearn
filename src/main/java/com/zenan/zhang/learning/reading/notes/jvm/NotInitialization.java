package com.zenan.zhang.learning.reading.notes.jvm;

public class NotInitialization {
    public static void main(String[] args) {
        // System.out.println(SubClass.value);
        //SuperClass[] su = new SuperClass[2];
        //System.out.println(ConstClass.HELLO_WORLD);

        SuperClass sc = new SubClass();
        sc = new SubClass();

    }
}
