package com.zenan.zhang.learning.reading.notes.Java高并发程序设计.第二章;
/**
 * synchronized 所用与给定对象instance,每次当线程进入被synchronized包裹的代码段，就会请教instance实例的锁，
 * 如果得到就执行，否则等待
 */
public class AccountingSync implements Runnable {
    static AccountingSync instance = new AccountingSync();
    static int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10000000; i++) {
            synchronized (instance) {
                count++;
            }
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
