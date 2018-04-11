package com.zenan.zhang.learning.reading.notes.multi.thread.chapter1;

public class MyThread3 extends Thread {
    public MyThread3() {
        System.out.println("构造函数：" + currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("run：" + currentThread().getName());
    }
}
