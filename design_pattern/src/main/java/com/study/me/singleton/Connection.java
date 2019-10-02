package com.study.me.singleton;

/**
 * 静态内部类单例模式
 * @author FanQie
 * @date 2019/10/1 22:06
 */
public class Connection {

    /**
     * 静态内部类持有单例实例
     */
    private static class InstanceHolder {
        private static final Connection INSTANCE = new Connection();
    }

    /**
     * 构造方私有化
     */
    private Connection() {
//        破解反射攻击 抛出异常
//        if (Objects.nonNull(InstanceHolder.INSTANCE)) {
//            throw new IllegalStateException();
//        }
    }

    /**
     * 暴露接口
     */
    public static Connection getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void open() {}

    public void close() {}
}
