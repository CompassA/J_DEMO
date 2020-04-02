package com.study.me.base;

/**
 * @author fanqie
 * @date 2020/4/2
 */
public class Father {

    static {
        System.out.println("1");
    }

    {
        System.out.println(3);
    }

    public Father() {
        System.out.println(5);
    }

    {
        System.out.println(4);
    }
}
