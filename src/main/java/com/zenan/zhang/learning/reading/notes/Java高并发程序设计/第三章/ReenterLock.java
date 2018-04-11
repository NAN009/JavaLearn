package com.zenan.zhang.learning.reading.notes.Java高并发程序设计.第三章;

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock lock = new ReenterLock();
        Thread t1 = new Thread(lock);
        Thread t2 = new Thread(lock);
        t1.start();
        t2.start();
        t2.join();
        t2.join();
        System.out.println(i);
    }
}
