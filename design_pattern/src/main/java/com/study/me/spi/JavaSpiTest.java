package com.study.me.spi;

import java.util.ServiceLoader;

/**
 * @author tomato
 * Created on 2020.10.04
 */
public class JavaSpiTest {

    public static void main(String[] args) {
        ServiceLoader<SpiInterface> serviceLoader = ServiceLoader.load(SpiInterface.class);
        serviceLoader.forEach(instance -> System.out.println(instance.echo("hello world")));
    }
}
