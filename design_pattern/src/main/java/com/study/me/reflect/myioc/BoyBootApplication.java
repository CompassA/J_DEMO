package com.study.me.reflect.myioc;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * pack -> absolute pack path -> list class -> register bean
 * bean field -> get setter -> reflection invoke
 *
 * @author fanqie
 * @date 2020/4/28
 */
public class BoyBootApplication {

    private static final String CLASS_SUFFIX = ".class";
    private static final String PROTOCOL_FILE = "file";
    private static final String UTF8_CHARSET = "UTF-8";
    private static final int DISCARD_LEN = 6;

    public static void run(final Class<?> rootClass) {
        try {
            final MyBeanFactory beanFactory = injectBeans(registerBeans(scanPackage(rootClass)));
            System.out.println(beanFactory);
        } catch (final IllegalAccessException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> scanPackage(final Class<?> rootClass) throws IOException {
        final String[] packages = rootClass.getAnnotation(BeanPackage.class).value();
        final List<String> targetClasses = new ArrayList<>(0);
        for (final String pack : packages) {
            final String packPath = pack.replace(".", File.separator);
            final Enumeration<URL> classes = rootClass.getClassLoader().getResources(packPath);

            while (classes.hasMoreElements()) {
                final URL curUrl = classes.nextElement();
                if (!PROTOCOL_FILE.equals(curUrl.getProtocol())) {
                    continue;
                }
                final String packAbsPath = URLDecoder.decode(curUrl.getFile(), UTF8_CHARSET);
                final File packDictionary = new File(packAbsPath);
                if (!packDictionary.isDirectory()) {
                    continue;
                }
                final File[] classFiles = packDictionary.listFiles();
                if (classFiles != null) {
                    for (final File clazz : classFiles) {
                        final String classFileName = clazz.getName();
                        if (classFileName.endsWith(CLASS_SUFFIX)) {
                            targetClasses.add(getClassFullName(pack, classFileName));
                        }
                    }
                }
            }
        }
        return targetClasses;
    }

    private static String getClassFullName(final String packName, final String clazzFileName) {
        return packName + "." + clazzFileName.substring(0, clazzFileName.length() - DISCARD_LEN);
    }

    private static MyBeanFactory registerBeans(final List<String> beanClasses)
            throws ClassNotFoundException {
        final MyBeanFactory beanFactory = new MyBeanFactory();
        for (final String clazzName : beanClasses) {
            final Class<?> clazz = Class.forName(clazzName);
            if (clazz.isAnnotationPresent(BeanMark.class)) {
                beanFactory.registerBean(clazz);
            }
        }
        return beanFactory;
    }

    private static MyBeanFactory injectBeans(final MyBeanFactory beanFactory)
            throws IllegalAccessException {
        for (final String beanName : beanFactory.getBeanNameSet()) {
            final Object bean = beanFactory.getBean(beanName);
            final Class<?> beanClazz = bean.getClass();
            for (final Field field : beanClazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(InjectionMark.class)) {
                    field.setAccessible(true);
                    field.set(bean, beanFactory.getBean(getTargetBeanName(field)));
                }
            }
        }
        return beanFactory;
    }

    private static String getTargetBeanName(final Field field) {
        final String beanName = field.getAnnotation(InjectionMark.class).beanName();
        if (beanName.isEmpty()) {
            return upperFirstChar(field.getName());
        }
        return beanName;
    }

    private static String upperFirstChar(final String str) {
        final char[] oldName = str.toCharArray();
        oldName[0] -= 32;
        return new String(oldName);
    }
}
