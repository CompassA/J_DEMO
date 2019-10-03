package com.study.me.proxy;

/**
 * @author FanQie
 * @date 2019/10/3 23:30
 */
public class WorkerGay implements SomeFunction {

    @Override
    public void doSomething() {
        System.out.println("WorkerGay : working");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
