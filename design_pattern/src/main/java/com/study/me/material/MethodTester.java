package com.study.me.material;

/**
 * @author fanqie
 * @date 2020/4/2
 */
public class MethodTester implements TestInterface {

    @Override
    public String printData(int a, int b) {
        return String.format("a = %d, b = %d", a, b);
    }

    @Override
    public String printData(Integer a, Integer b) {
        return String.format("a = %d, b = %d", a, b);
    }
}
