package com.zenan.zhang.learning.reading.notes.multi.thread.chapter1;

import org.junit.Test;

public class Run {
    public static void main(String[] strings) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("运行结束！");
    }

    /**
     * 线程在start()处无序
     * 在run（）内部有序
     */
    @Test
    public void testThread() {
        try {
            MyThread myThread = new MyThread();
            myThread.setName("MyThread");
            myThread.start();
            for (int i = 0; i < 10; i++) {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("main=" + Thread.currentThread().getName() + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRunnable() {
        Runnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("运行结束！");
    }


    /**
     * 不共享线程间数据
     */
    @Test
    public void testCount() {
        final MyThread1 my1 = new MyThread1("A");
        MyThread1 my2 = new MyThread1("B");
        MyThread1 my3 = new MyThread1("C");
        my1.start();
        my2.start();
        my3.start();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("123");
            }
        });
    }

    /**
     * 共享线程间数据
     */
    @Test
    public void testShareCount() {
        MyThread2 thread = new MyThread2();
        Thread a = new Thread(thread, "A");
        Thread b = new Thread(thread, "B");
        Thread c = new Thread(thread, "C");
        Thread d = new Thread(thread, "D");
        Thread e = new Thread(thread, "E");
        Thread f = new Thread(thread, "F");

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
        f.start();
    }

    /**
     * 一旦调用.start()就会新开线程执行run方法
     */
    @Test
    public void testMyThread3() {
        MyThread3 my = new MyThread3();
        my.start();
    }

    /**
     * 调用run却不会开新线程
     */
    @Test
    public void testMyThread31() {
        MyThread3 my = new MyThread3();
        my.run();
    }
}
