package com.zenan.zhang.learning.reading.notes.multi.thread.chapter1;
/**
 * 不共享数据
 */
public class MyThread1 extends Thread {
    private int count = 5;

    public MyThread1(String name) {
        super();
        setName(name);
    }

    @Override
    public void run() {
        super.run();
        while (count > 0) {
            count--;
            System.out.println("由" + currentThread().getName() + "计算，count=" + count);
        }
    }
}
