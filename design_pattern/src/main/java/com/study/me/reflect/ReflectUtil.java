package com.study.me.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author FanQie
 * @date 2019/10/1 19:54
 */
public class ReflectUtil {

    private ReflectUtil() { }

    public static void printClassMessage(Class<?> classIns) {
        Method[] methods = classIns.getDeclaredMethods();
        System.out.println(classIns.getName());

        for (final Method method : methods) {
            System.out.printf("%s %s(",
                    method.getReturnType().getSimpleName(),
                    method.getName());
            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; ++i) {
                System.out.printf("%s%s",
                        parameters[i].getType().getSimpleName(),
                        i == parameters.length - 1 ? "" : ", ");
            }
            System.out.println(")");
        }
    }
}
