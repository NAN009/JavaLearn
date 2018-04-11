package com.zenan.zhang.learning.reading.notes.Java高并发程序设计.第二章;
/**
 * 使用synchronized 作用于实例方法，在进入increase前，线程必须获得当前实例对象的锁
 */
public class AccountingSync2 implements Runnable {
    static AccountingSync2 instance = new AccountingSync2();
    static int count = 0;

    public synchronized void increase() {
        count++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000000; i++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t2 = new Thread(instance);
        Thread t1 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }
}
