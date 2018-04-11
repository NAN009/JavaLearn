package com.zenan.zhang.learning.reading.notes.jvm;

public class StaticDispatch {
    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {

    }

    public void sayHello(Human human) {
        System.out.println("hello guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman woman) {
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        StaticDispatch staticDispatch = new StaticDispatch();
        String a = "A";
        String b = "B";
        String c = "C";
        String s2 = a + b + c;
        String s = staticDispatch.concatString(a, b, c);
        System.out.println(s);
    }

    public String concatString(String s1, String s2, String s3) {
        StringBuffer sb = new StringBuffer();
        sb.append(s1).append(s2).append(s3);
        return sb.toString();
    }
}
