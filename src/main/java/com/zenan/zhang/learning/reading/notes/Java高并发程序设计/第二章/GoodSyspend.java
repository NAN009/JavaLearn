package com.zenan.zhang.learning.reading.notes.Java高并发程序设计.第二章;

public class GoodSyspend {
    public static Object u = new Object();

    public static class ChangeObjectThread extends Thread {
        volatile boolean suspendme = false;

        public void suspendMe() {
            suspendme = true;
        }

        public void resumeMe() {
            suspendme = false;
            synchronized (this) {
                notify();
            }
        }

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    while (suspendme) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (u) {
                    System.out.println(" in ChangeObjectThread");
                }
                Thread.yield();
            }
        }
    }
    public static class ReadObjectThread extends  Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    System.out.println(" in ReadObjectThread");
                }
                Thread.yield();
            }
        }
    }
    public static void main(String[] args) throws  InterruptedException{
        ChangeObjectThread t1= new ChangeObjectThread();
        ReadObjectThread t2 = new ReadObjectThread();
        t1.start();
        t2.start();
        Thread.sleep(100);
        t1.suspendMe();
        System.out.println("=====suspend t1 2 sec");
        Thread.sleep(200);
        System.out.println("=====resume t1");
        t1.resumeMe();
    }
}
