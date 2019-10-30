import com.study.me.RedisLock;
import com.study.me.RedisUtil;
import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author FanQie
 * @date 2019/10/26 16:44
 */
public class RedisTest {

    private static int cnt = 0;

    @Test
    public void redisTest() {
        final Jedis jedis = RedisUtil.getJedis();
        jedis.set("hhh", "bbb");
        System.out.println(jedis.get("hhh"));
    }

    @Test
    public void redisLockTest() throws InterruptedException {
        final int TIMES = 10000;

        final Runnable function = () -> {
            final RedisLock redisLock = new RedisLock(RedisUtil.getJedis());

            for (int i = 0; i < TIMES; ++i) {
                while (!redisLock.lock()) { }
                ++RedisTest.cnt;
                System.out.println("cnt = " + RedisTest.cnt);
                redisLock.unlock();
            }

        };
        final Thread t1 = new Thread(function);
        final Thread t2 = new Thread(function);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(RedisTest.cnt);
        Assert.assertEquals(RedisTest.cnt, TIMES * 2);


    }
}
