package com.study.me.dynamicproxy;

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

    /**
     * 被代理对象
     */
    private final Object obj;

    public TimeHandler(final Object obj) {
        this.obj = obj;
    }

    /**
     * 传入类构造器、需要被代理的接口、拦截器
     * JDK构造一个代理类，该类持有拦截器引用，并实现了各个接口（反射）
     * 每个接口方法调用拦截器的invoke方法，实现代理
     *
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
     * @param proxy 代理对象
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
