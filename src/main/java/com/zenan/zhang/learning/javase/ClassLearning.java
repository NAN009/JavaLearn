package com.zenan.zhang.learning.javase;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassLearning {

    private int l;
    private Long aLong = 1L;
    public int p;

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public ClassLearning() {

    }

    private ClassLearning(int i) {

    }

    private void A(){}

    /**
     * String getName() 得到全限定名 如：java.lang.String
     * ClassLoader getClassLoader() 得到类加载器
     */
    @Test
    public void test() {
        String s = "abc";
        System.out.println(s.getClass());
        Assert.assertEquals(s.getClass().toString(), "class java.lang.String");

        System.out.println(s.getClass().getName());
        Assert.assertEquals(s.getClass().getName(), "java.lang.String");

        System.out.println(s.getClass().getClassLoader());

        System.out.println(s.getClass().getTypeParameters());
        System.out.println(s.getClass().getGenericSuperclass());

        Assert.assertEquals(s.getClass().getPackage().getName(), "java.lang");
        System.out.println(s.getClass().getPackage().getName());

        Assert.assertEquals(s.getClass().getSimpleName(), "String");
        Class s1 = String.class;
        Class sd=int.class;
        System.out.println();
    }


    @Test
    public void test1() {
        String s = "abc";
        System.out.println(s.getClass().getDeclaredClasses());
        ClassLearning classLearning = new ClassLearning();
        System.out.println(classLearning.getClass().getDeclaredClasses());
        Field[] fields = classLearning.getClass().getFields();
        System.out.println(fields[0].toString());
    }

    /**
     * Field[] getFields()
     * 返回public（公共的）的成员变量
     */
    @Test
    public void testGetFields() {
        ClassLearning classLearning = new ClassLearning();
        Field[] fields = classLearning.getClass().getFields();
        Field field = fields[0];
        Field[] f1 = classLearning.getClass().getDeclaredFields();
        field.setAccessible(true);
        try {
            Object object = field.get(classLearning);
            System.out.println(fields[0].toString());

        } catch (Exception e) {

        }

        System.out.println(fields[0].toString());
    }

    /**
     * Method[] getMethods()
     * 返回公共的（public）方法
     */
    @Test
    public void testGetMethods() {
        ClassLearning classLearning = new ClassLearning();
        Method[] methods = classLearning.getClass().getMethods();
        System.out.println(methods[0].toString());
        Method[] m1 = classLearning.getClass().getDeclaredMethods();
        System.out.println(m1);
        m1[0].setAccessible(true);


    }
}
