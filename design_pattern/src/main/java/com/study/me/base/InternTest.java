package com.study.me.base;

/**
 * Java8 若字符串值在常量池存在，返回常量池应用；否则将自身引用添加至常量池
 * @author fanqie
 * @date 2020/4/10
 */
public class InternTest {

    public static void main(String[] args) {
        //false
        String s1 = new String("abc");
        String s2 = "abc";
        String s3 = s2.intern();
        System.out.println(s1 == s3);

        //true
        String s4 = new String("a") + new String("b");
        String s5 = s4.intern();
        String s6 = "ab";
        System.out.println(s5 == s6);
    }
}
