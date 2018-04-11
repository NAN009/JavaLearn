package com.zenan.zhang.learning.reading.notes.multi.thread.chapter1;

public class MyThread extends Thread {

    /**
     * 本章关键点
     * 线程启动、暂停、停止
     * 线程优先级，线程安全
     *
     */


    /**
     * 多线程的方式
     * 1、继承Thread类
     * 2、实现Runnable接口
     */
    @Override
    public void run() {
        super.run();
        System.out.println(Thread.currentThread().getName());
        try {
            for (int i = 0; i < 10; i++) {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("run=" + Thread.currentThread().getName()+i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
