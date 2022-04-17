import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public final class RedisUtil {
    private static final Jedis jedis;
    static {
        JedisPool pool = new JedisPool(new GenericObjectPoolConfig(), "120.78.15.216", 6379, 10000, "jiangjie1");
        jedis = pool.getResource();
    }

    public static Jedis getJedis() {
        return jedis;
    }
}
