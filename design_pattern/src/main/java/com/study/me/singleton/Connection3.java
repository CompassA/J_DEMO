package com.study.me.singleton;

/**
 * @author fanqie
 * @date 2020/4/6
 */
public class Connection3 {

    private Connection3() {
        if (InnerHolder.INSTANCE != null) {
            throw new IllegalStateException();
        }
    }

    public Connection3 getInstance() {
        return InnerHolder.INSTANCE;
    }

    private static class InnerHolder {
        private static Connection3 INSTANCE = new Connection3();
    }
}
