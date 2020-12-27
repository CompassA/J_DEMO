package com.study.me.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tomato
 * Created on 2020.12.27
 */
@RestController
public class AwareDemo implements ApplicationContextAware, BeanNameAware {

    public ApplicationContext applicationContext;

    public String beanName;

    @GetMapping("/test/aware")
    public String aware() {
        System.out.println(applicationContext);
        return beanName;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
