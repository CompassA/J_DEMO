package com.study.me.reflect.myioc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author fanqie
 * @date 2020/4/28
 */
public class MyBeanFactory {

    private final Map<String, Object> beanMap;

    public MyBeanFactory() {
        beanMap = new HashMap<>();
    }

    public Object getBean(final String beanName) {
        return beanMap.get(beanName);
    }

    public Set<String> getBeanNameSet() {
        return beanMap.keySet();
    }

    public void registerBean(final Class<?> clazz) {
        try {
            final String markName = clazz.getAnnotation(BeanMark.class).name();
            final String beanName = markName.isEmpty() ? clazz.getSimpleName() : markName;
            beanMap.put(beanName, clazz.getConstructor().newInstance());
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
