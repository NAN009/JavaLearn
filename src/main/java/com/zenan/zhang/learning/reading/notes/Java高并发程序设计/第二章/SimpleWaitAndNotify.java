package com.zenan.zhang.learning.reading.notes.Java高并发程序设计.第二章;

public class SimpleWaitAndNotify {
    final static Object object = new Object();

    public static class T1 extends Thread {
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T1 start!");
                try {
                    System.out.println(System.currentTimeMillis() + ":T1 wait for object ");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end!");
            }
        }
    }

    public static class T2 extends Thread {
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T2 start! notify on thread ");
                object.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end!");

                try {
                    System.out.println(System.currentTimeMillis()+":T2 sleep begin!");
                    Thread.sleep(2000);
                    System.out.println(System.currentTimeMillis()+":T2 sleep end!");
                } catch (InterruptedException e) {

                }
            }

        }

    }

    public static void main(String[] s) throws InterruptedException{
        Thread t1 = new T1();
        Thread t2 = new T2();

        t1.start();
        Thread.sleep(100);//由于t1 t2执行run()先后随机，如去掉本行则有可能t2先执行，那么t1将永远等待
        t2.start();
    }
}
