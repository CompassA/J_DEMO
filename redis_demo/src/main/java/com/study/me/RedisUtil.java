package com.study.me;

import redis.clients.jedis.Jedis;

/**
 * @author FanQie
 * @date 2019/10/26 16:40
 */
public class RedisUtil {

    private static final String HOST = "192.168.37.131";

    private static final int PORT = 6379;

    @Deprecated
    public static Jedis getJedis() {
        return new Jedis(HOST, PORT);
    }

}
