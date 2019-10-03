package com.study.me.dynamicproxy;

import com.study.me.proxy.SomeFunction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 自定义handler类，实现InvocationHandler接口
 * @author FanQie
 * @date 2019/10/3 23:55
 */
public class TimeHandler implements InvocationHandler {

    private final SomeFunction obj;

    public TimeHandler(final SomeFunction obj) {
        this.obj = obj;
    }

    /**
     * @return 动态代理类
     */
    public Object newProxyInstance() {
        return Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                this);
    }

    /**
     * 动态代理
     * @param proxy 被代理的对象
     * @param method 被代理的方法
     * @param args 方法的参数
     * @return method的返回类型
     */
    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args)
            throws InvocationTargetException, IllegalAccessException {
        final long begin = System.currentTimeMillis();
        System.out.println("begin : " + begin);

        final Object returnValue = method.invoke(obj, args);

        final long end = System.currentTimeMillis();
        System.out.println("end : " + end);

        System.out.println("cost : " + (end - begin) +"ms" );
        return returnValue;
    }

}
