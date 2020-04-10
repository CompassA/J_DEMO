package com.study.me.base;

/**
 * -verbose: class
 * -XX: +TraceClassLoading
 * -XX: +TraceClassUnloading
 * 类初始化时机测试
 * @author fanqie
 * @date 2020/4/6
 */
public class LoadTest {

    private static class InnerHolder {
        private static final int CONST = 1;
        private static int var = 2;

        static {
            System.out.println("InnerHolder被加载");
        }
    }

    public static void main(String[] args) {
        //获取静态常量，类不会被加载
        System.out.println("获取InnerHolder的const：" + InnerHolder.CONST);
        //获取静态变量，类会被加载
        System.out.println("获取InnerHolder的var：" + InnerHolder.var);
    }
}
