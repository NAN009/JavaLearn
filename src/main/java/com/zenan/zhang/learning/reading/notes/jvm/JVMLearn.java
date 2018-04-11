package com.zenan.zhang.learning.reading.notes.jvm;

public class JVMLearn {
    private static final String JVM_TEST = "JVM";

    public static void main(String[] args) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        long  totalMemory = Runtime.getRuntime().totalMemory();

        long lowerL = 123;
        Long upperL = new Long(lowerL);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }

        System.out.println(upperL);
    }
}
