package com.zenan.zhang.learning.reading.notes.Java高并发程序设计.第二章;

/**
 * 由于代码18 19行两个线程指向不同的Runnable实例,导致线程t1进入同步方法前加锁自己的Runnable实例，t2也锁自己的对象；
 * 两个线程持有不同的锁，并不是线程安全的
 */
public class AccountingSyncBad implements Runnable {
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
        Thread t2 = new Thread(new AccountingSyncBad());
        Thread t1 = new Thread(new AccountingSyncBad());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }
}
