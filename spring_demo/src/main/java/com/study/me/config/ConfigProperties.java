package com.study.me.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author fanqie
 * Created on 2020.09.18
 */
@ConfigurationProperties(prefix = "org.study.spring")
public class ConfigProperties {

    private int argVal1 = 0;

    private int argVal2 = 0;

    private String argVal3 = "default";

    public int getArgVal1() {
        return argVal1;
    }

    public void setArgVal1(int argVal1) {
        this.argVal1 = argVal1;
    }

    public int getArgVal2() {
        return argVal2;
    }

    public void setArgVal2(int argVal2) {
        this.argVal2 = argVal2;
    }

    public String getArgVal3() {
        return argVal3;
    }

    public void setArgVal3(String argVal3) {
        this.argVal3 = argVal3;
    }

    @Override
    public String toString() {
        return "ConfigProperties{" +
                "argVal1=" + argVal1 +
                ", argVal2=" + argVal2 +
                ", argVal3='" + argVal3 + '\'' +
                '}';
    }
}
