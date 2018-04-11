package com.zenan.zhang.learning.reading.notes.multi.thread.chapter1;

public class MyThread2 extends Thread {
    private int count = 10;

    @Override
    public void run() {
        super.run();
        count--;
        System.out.println("由" + currentThread().getName() + "计算，count=" + count);
    }

}
