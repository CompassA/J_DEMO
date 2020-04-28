package com.study.me.reflect.mybean;

import com.study.me.reflect.myioc.BeanMark;
import com.study.me.reflect.myioc.InjectionMark;

/**
 * @author fanqie
 * @date 2020/4/28
 */
@BeanMark
public class TestBeanOther {

    @InjectionMark
    private TestBean testBean;

    @InjectionMark(beanName = "implB")
    private BeanInterface beanInterface;

    public BeanInterface getBeanInterface() {
        return beanInterface;
    }

    public void setBeanInterface(final BeanInterface beanInterface) {
        this.beanInterface = beanInterface;
    }

    public TestBean getTestBean() {
        return testBean;
    }

    public void setTestBean(final TestBean testBean) {
        this.testBean = testBean;
    }


}
