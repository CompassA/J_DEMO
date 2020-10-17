package com.study.me.spi;

/**
 * @author tomato
 * Created on 2020.10.04
 */
public class SpiInterfaceImplOne implements SpiInterface {

    @Override
    public String echo(String str) {
        return "class one: " + str;
    }
}
