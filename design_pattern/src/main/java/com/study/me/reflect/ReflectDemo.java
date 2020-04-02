package com.study.me.reflect;

import com.study.me.material.TestInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author fanqie
 * @date 2020/4/2
 */
public class ReflectDemo {

    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException,
            InstantiationException {
        Class clazz = Class.forName("com.study.me.material.MethodTester");

        Constructor<TestInterface> constructor = clazz.getConstructor();

        Method methodA = clazz.getDeclaredMethod("printData", int.class, int.class);
        Method methodB = clazz.getDeclaredMethod("printData", Integer.class, Integer.class);

        Object instance = constructor.newInstance();

        Object resOfA = methodA.invoke(instance, 1, 2);
        Object resOfB = methodB.invoke(instance, 1, 2);

        System.out.println(resOfA);
        System.out.println(resOfB);
    }
}
