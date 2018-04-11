package com.zenan.zhang.learning.reading.notes.Java高并发程序设计.第二章;
/**
 * 修改AccountingSyncBad 中increase方法为静态的，这样就作用于当前类的锁，并非当前实例的锁
 */
public class AccountingSync3 implements Runnable {
    static int count = 0;

    public static synchronized void increase() {
        count++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000000; i++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t2 = new Thread(new AccountingSync3());
        Thread t1 = new Thread(new AccountingSync3());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }
}

