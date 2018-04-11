package com.zenan.zhang.learning.reading.notes.Java高并发程序设计.第二章;
/**
 * 如果不设置为守护线程，线程t将永远执行
 */
public class DaemonDemo {
    public static class DaemonT extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("I am alive");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonT();
        t.setDaemon(true);//如果注释掉此行，即使main线程结束，守护线程仍然执行，打出I am alive
        t.start();

        Thread.sleep(2000);

        System.out.println("main end!");
    }
}
