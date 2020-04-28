package com.study.me.reflect.myioc;

/**
 * @author fanqie
 * @date 2020/4/28
 */
@BeanPackage(value = {"com.study.me.reflect.mybean"})
public class MiniStarter {

    public static void main(final String[] args) {
        BoyBootApplication.run(MiniStarter.class);
    }
}
