package com.study.me.base;

/**
 * @author fanqie
 * @date 2020/4/2
 */
public class Son extends Father {

    static {
        System.out.println(2);
    }

    {
        System.out.println(6);
    }

    public Son() {
        System.out.println(8);
    }

    {
        System.out.println(7);
    }

    /**
     * 1
     * 2
     * ----------------
     * 3
     * 4
     * 5
     * 6
     * 7
     * 8
     */
    public static void main(String[] args) {
        Class<Son> son = Son.class;
        System.out.println("---------------------------");
        new Son();
    }
}
