package com.study.me.reflect.mybean;

import com.study.me.reflect.myioc.BeanMark;
import com.study.me.reflect.myioc.InjectionMark;

/**
 * @author fanqie
 * @date 2020/4/28
 */
@BeanMark
public class TestBean {

    @InjectionMark
    private TestBeanOther testBeanOther;

    @InjectionMark(beanName = "implA")
    private BeanInterface beanInterface;

    public BeanInterface getBeanInterface() {
        return beanInterface;
    }

    public void setBeanInterface(final BeanInterface beanInterface) {
        this.beanInterface = beanInterface;
    }

    public TestBeanOther getTestBeanOther() {
        return testBeanOther;
    }

    public void setTestBeanOther(final TestBeanOther testBeanOther) {
        this.testBeanOther = testBeanOther;
    }
}
