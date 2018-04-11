package com.zenan.zhang.learning.reading.notes.Java高并发程序设计.第二章;

/**
 * 大部分情况下高优先级线程都会先完成
 */
public class PriorityDemo {
    public static class HightPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("hightPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }

        }
    }

    public static void main(String[] str) {
        Thread high = new HightPriority();
        LowPriority low = new LowPriority();
        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        high.start();
        low.start();
    }
}
