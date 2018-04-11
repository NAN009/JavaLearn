package com.zenan.zhang.learning.reading.notes.java.web;

import java.util.concurrent.Semaphore;

/**
 * Created by BG225078 on 2017/11/5.
 */
public class JavaWeb {

    public void HttpClientCall() {
        Semaphore semaphore = new Semaphore(4);
        semaphore.availablePermits();

    }


}
