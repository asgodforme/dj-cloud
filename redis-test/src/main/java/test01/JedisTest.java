package test01;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/*
带密码启动redis： docker run -d --name redis1 -p 6379:6379 redis --requirepass "jiangjie1"
访问redis-cli: docker exec -it redis1 redis-cli 然后输入auth 你的密码。
 */
public class JedisTest {

    private static final Jedis jedis;
    static {
        JedisPool pool = new JedisPool(new GenericObjectPoolConfig(), "120.78.15.216", 6379, 10000, "jiangjie1");
        jedis = pool.getResource();
    }
    public static void main(String[] args) {
        System.out.println(jedis.get("a"));
    }

    /*
    （1）获取锁的时候，使用setnx加锁，并使用expire命令为锁添加一个超时时间，超过该时间则自动释放锁，锁的value值为一个随机生成的UUID，通过此在释放锁的时候进行判断。
    （2）获取锁的时候还设置一个获取的超时时间，若超过这个时间则放弃获取锁。
    （3）释放锁的时候，通过UUID判断是不是该锁，若是该锁，则执行delete进行锁释放。
     */
    public boolean acquireLock(String lockName, long acquireTime, long timeout) {
        String lockValue = UUID.randomUUID().toString();
        long end = System.currentTimeMillis() + acquireTime;
        String lock = "string:lock" + lockName;
        while (System.currentTimeMillis() < end) {
            // 加锁成功
            if (jedis.setnx(lock, lockValue) == 1) {
                jedis.expire(lock, timeout);
                return true;
            // 锁已经超时释放
            } else if (jedis.ttl(lock) == 0) {
                jedis.expire(lock, timeout);
            }
        }
        return false;
    }

    public void releaseLock(String lockName) {
        String lock = "string:lock" + lockName;
        while (true) {
            String result = jedis.get(lock);
            /*if (StringUtils.isNotEmpty(result)) {
                jedis.del(lockName);
                return;
            }*/
        }
    }
}
