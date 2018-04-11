package com.zenan.zhang.learning.reading.notes.Java高并发程序设计.第二章;
/**
 * 多个线程同时写count,导致count最终值并非是20000000
 */
public class AccountingVol implements Runnable {
    static AccountingVol instance = new AccountingVol();
    static volatile int count = 0;

    public static void increase() {
        count++;
    }

    @Override
    public void run() {
        for (int i = 0; i <10000000; i++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }

}
