package com.study.me;

import redis.clients.jedis.Jedis;

import java.util.Objects;

/**
 * @author FanQie
 * @date 2019/10/30 16:46
 */
public class RedisLock {

    private Jedis jedis;

    private static final String LOCK_NAME = "lock";

    public RedisLock(final Jedis jedis) {
        this.jedis = jedis;
    }

    public boolean lock() {
        return Objects.nonNull(
                jedis.set(LOCK_NAME, "true", "nx", "ex", 1L)
        );
    }

    public void unlock() {
        jedis.del(LOCK_NAME);
    }
}
