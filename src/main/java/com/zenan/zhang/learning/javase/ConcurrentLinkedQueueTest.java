package com.zenan.zhang.learning.javase;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 1、非阻塞线程安全队列
 * 2、基于链接节点的无界线程安全队列
 * 3、先进先出，添加元素在队尾，获取元素在列头
 * 4、wait-free 算法实现（CAS算法 Compare and Swap）
 */
public class ConcurrentLinkedQueueTest {
    @Test
    public void testConcurrentLinkedQueue(){
        ConcurrentLinkedQueue linkedQueue = new ConcurrentLinkedQueue();
        linkedQueue.add(0);
        linkedQueue.add(1);
        linkedQueue.add(3);
    }
}
