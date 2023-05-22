package test02;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPool;

public class TestRedis {

    private static final Jedis jedis;
    static {
        JedisPool pool = new JedisPool(new GenericObjectPoolConfig(), "120.78.15.216", 6379, 10000);
        jedis = pool.getResource();
    }

    public static void main(String[] args) {
        System.out.println(jedis.get("key"));
        System.out.println(jedis.incr("key"));
        System.out.println(jedis.get("a"));
    }
}
