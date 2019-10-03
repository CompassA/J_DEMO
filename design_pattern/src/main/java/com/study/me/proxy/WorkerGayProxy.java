package com.study.me.proxy;

/**
 * 静态代理
 * 同样功能的前提下，一个对象写一个代理, bad!
 * @author FanQie
 * @date 2019/10/3 23:33
 */
public class WorkerGayProxy implements SomeFunction {

    /**
     * 实现类与代理类实现同一个接口
     * 代理类持有实现类引用
     */
    private final SomeFunction workerGay;

    public WorkerGayProxy(final WorkerGay workerGay) {
        this.workerGay = workerGay;
    }

    @Override
    public void doSomething() {
        final long begin = System.currentTimeMillis();
        System.out.println("doSomething begin: " + begin);

        workerGay.doSomething();

        final long end = System.currentTimeMillis();
        System.out.println("doSomething end: " + end);
        System.out.println("cost : " + (end - begin) + "ms");
    }
}
