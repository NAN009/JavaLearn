package com.zenan.zhang.learning.javase;


import org.junit.Assert;
import org.junit.Test;

import java.io.ObjectStreamField;


/**
 * @author zenan
 * String源码学习
 */
public class StringLearning {

    /**
     * 1、String是字符串常量，被final修饰，在创建后不能更改，正因为其不可变，所以可以共享，字符串缓冲区支持可变的字符串常量
     * 2、String表示一个UTF-16格式的字符串
     *
     */
    //字段解析
    /**
     * 存放String值
     */
    private final char[] value = {};

    /**
     * 缓存hash code ，默认0
     */
    private int hash;

    private static final long serialVersionUID = -1L;

    private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[0];


   /* */

    /**
     * 常用方法
     */
    @Test
    public void testLearning1() {

        String s = null;
        //空字符串 调用length()、isEmpty()会抛出NullPointerException
        try {
            Assert.assertEquals(s.length(), 0);
        } catch (NullPointerException e) {
            System.out.println("s is null,s.length() exception is :" + e.getClass());
            e.printStackTrace();
        }
        try {
            Assert.assertTrue(s.isEmpty());
        } catch (NullPointerException e) {
            System.out.println("s is null,s.length() exception is :" + e.getClass());
            e.printStackTrace();
        }


        s = "123456xyz好";
        char c = '1';
        System.out.println("s:" + s);
        System.out.println("s length:" + s.length());
        System.out.println("好 length:" + "好".length());

        Assert.assertEquals(s.charAt(0), c);
        System.out.println(s.charAt(s.length() - 1));
        System.out.println("charAt 好 is:" + s.charAt(s.length() - 1));

        Assert.assertEquals(s.codePointAt(0), c);
        System.out.println("codePointAt 好 is:" + s.codePointAt(s.length() - 1));
        Assert.assertEquals(s.codePointAt(s.length() - 2), 122);//z ascii 112
        System.out.println("codePointAt z is:" + s.codePointAt(s.length() - 2));
        System.out.println("codePointAt 6 is:" + s.codePointAt(5));

        Assert.assertEquals(s.codePointBefore(s.length()), s.codePointAt(s.length() - 1));


    }

    /**
     * public native String intern();
     * 调用本地方法，在方法区的常量池里通过equals方法寻找等值对象，
     * 如果没找到就新开辟一个空间存放并返回String引用，如果找到就返回已存在对象引用
     */
    @Test
    public void testEquals() {
        String s = "123456xyz好";
        String s1 = new String("123456xyz好");
        Assert.assertEquals(s, s1);
        Assert.assertTrue(s.equals(s1));
        Assert.assertFalse(s == s1);
        Assert.assertTrue(s==s.intern());

        System.out.print("s==s1 is: ");
        System.out.println(s == s1);
        System.out.print("s.equals(s1) is: ");
        System.out.println(s.equals(s1));
    }

    /**
     * String equals源码
     */
    public boolean equals(Object anObject) {
        //地址相同
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String anotherString = (String) anObject;
            int n = value.length;
            if (n == anotherString.toCharArray().length) {
                char var1[] = value;
                char var2[] = anotherString.toCharArray();
                int i = 0;
                while (n-- != 0) {
                    if (var1[i] != var2[i]) {
                        return false;
                    }
                    i++;
                }
            }
        }
        return false;
    }


    @Test
    public void testEqualsIgnoreCase() {
        String s = "abcdefg";
        //equalsIgnoreCase 将字符串与另一字符串比较忽略大小写
        Assert.assertTrue(s.equalsIgnoreCase(s));
        System.out.println(s.equalsIgnoreCase(s));
        Assert.assertTrue("ABCDEFG".equalsIgnoreCase(s));
        System.out.println("ABCDEFG".equalsIgnoreCase(s));
    }

    /**
     * equalsIgnoreCase 源码
     * 将字符串与另一字符串比较忽略大小写
     */
   /* public boolean equalsIgnoreCase(String anotherString) {
        return (this == anotherString) ? true :
                (anotherString != null)
                        && (anotherString.toCharArray().length == value.length)
                        && regionMatches(true, 0, anotherString, 0, value.length);
    }*/
    public boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len) {
        char ta[] = value;
        int to = toffset;
        char pa[] = other.toCharArray();
        int po = ooffset;
        if ((ooffset < 0) || (toffset < 0)
                || (toffset > (long) value.length - len)
                || (ooffset > (long) other.toCharArray().length - len)) {
            return false;
        }
        while (len-- > 0) {
            char c1 = ta[to++];
            char c2 = pa[po++];
            if (c1 == c2) {
                continue;
            }
            if (ignoreCase) {
                char u1 = Character.toUpperCase(c1);
                char u2 = Character.toUpperCase(c2);
                if (u1 == u2) {
                    continue;
                }
                if (Character.toLowerCase(u1) == Character.toLowerCase(u2)) {
                    continue;
                }
            }
            return false;
        }
        return true;
    }

    @Test
    public void testCompareTo() {
        String s = "abcdefg";
        String s1 = "abcdefg";
        System.out.print("s.compareTo(s1):");
        System.out.println(s.compareTo(s1));

        System.out.print("\"abcdefh\".compareTo(\"abcdefg\")");
        System.out.println("abcdefh".compareTo("abcdefg"));

        System.out.print("\"abcdefi\".compareTo(\"abcdefg\"): ");
        System.out.println("abcdefi".compareTo("abcdefg"));
        System.out.print("\"abcdefgh\".compareTo(\"abcdefg\"): ");
        System.out.println("abcdefgh".compareTo("abcdefg"));

        System.out.print("\"ABCDEFG\".compareToIgnoreCase(\"abcdefg\"): ");
        System.out.println("ABCDEFG".compareToIgnoreCase("abcdefg"));

    }

    public int compareTo(String anotherString) {
        int len1 = value.length;
        int len2 = anotherString.length();
        int lim = Math.min(len1, len2);
        char[] v1 = value;
        char[] v2 = anotherString.toCharArray();

        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;

    }


    public boolean startsWith(String prefix, int toffset) {
        char ta[] = value;
        int to = toffset;

        char pa[] = prefix.toCharArray();
        int po = 0;
        int pc = prefix.length();
        if ((toffset < 0) || (toffset > value.length - pc)) {
            return false;
        }
        while (--pc >= 0) {
            if (ta[to++] != pa[po++]) {
                return false;
            }
        }
        return true;
    }

    /**
     * boolean startsWith(String prefix, int toffset)
     * 从第toffset个字符开始比较，字符串的前缀是否是prefix
     * <p>
     * boolean startsWith(String prefix)
     * 字符串的前缀是否是prefix(从第0个字符开始比较是否字符串是否以prefix开始)
     */
    @Test
    public void testStartWith() {
        Assert.assertTrue("abc".startsWith("abc"));
        Assert.assertTrue("abc".startsWith("ab"));

        Assert.assertTrue("abc".startsWith("bc", 1));
        Assert.assertFalse("abc".startsWith("bc", 2));
        System.out.println("abc".startsWith("bc", 1));
    }

    /**
     * boolean endsWith(String suffix)
     * 字符串后缀是否是suffix
     */
    @Test
    public void testEndWith() {
        Assert.assertTrue("abcd".endsWith("d"));
        Assert.assertFalse("abcd".endsWith("dc"));
    }

    /**
     * int hashCode()
     * 返回hashCode
     */
    @Test
    public void testHashCode() {
        String s = "0";
        System.out.println(s.hashCode());
    }

    /**
     * int indexOf(int ch)
     * 返回第一个ch的下标
     * <p>
     * int indexOf(int ch, int fromIndex)
     * 从fromIndex开始，返回第一个ch的下标
     * <p>
     * int lastIndexOf(int ch)
     * <p>
     * int indexOf(String str)
     * <p>
     * int indexOf(String str, int fromIndex)
     * <p>
     * int lastIndexOf(String str)
     * <p>
     * int lastIndexOf(String str,int fromIndex)
     */
    @Test
    public void testIndexOf() {
        String s = "01";
        Assert.assertEquals(s.indexOf(48), 0);
        Assert.assertEquals(s.indexOf(49), 1);
        System.out.println(s.indexOf(48));

        s = "012345678902";
        Assert.assertEquals(s.indexOf(48, 2), 10);

        Assert.assertEquals(s.lastIndexOf(48), 10);
        Assert.assertEquals(s.lastIndexOf(47), -1);

        Assert.assertEquals(s.indexOf("1"), 1);

        Assert.assertEquals(s.indexOf("02"), 10);
    }

    /**
     * 从beginIndex开始截取子串
     * String subString(int beginIndex)
     * <p>
     * 截取子串从beginIndex开始，endIndex结束，不包含endIndex
     * String subString(int beginIndex,endIndex)
     */
    @Test
    public void testSubString() {
        String s = "abcde";
        System.out.println(s.substring(1));
        Assert.assertEquals("bcde", s.substring(1));

        Assert.assertEquals("bc", s.substring(1, 3));
    }

    /**
     * String concat(String str)
     */
    @Test
    public void testConcat() {
        String s = "abc";
        System.out.println(s.concat("012"));
    }

    @Test
    public void testReplace() {
        String s = "abcd";
        System.out.println(s.replace('a', '0'));
    }

    /**
     * String trim()
     * 去除开头和末尾的空白
     */
    @Test
    public void testTrim() {
        System.out.println("   abc   b   ".trim());
        Assert.assertEquals("   abc   b   ".trim(), "abc   b");
    }


}
