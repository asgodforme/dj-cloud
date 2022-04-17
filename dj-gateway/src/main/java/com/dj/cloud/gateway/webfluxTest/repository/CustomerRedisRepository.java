package com.dj.cloud.gateway.webfluxTest.repository;

import com.dj.cloud.gateway.webfluxTest.config.RedisConfig;
import com.dj.cloud.gateway.webfluxTest.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Repository
@ConditionalOnBean(RedisConfig.class)
public class CustomerRedisRepository {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /*
    ValueOperations  key-value
    ListOperations  列表
    SetOperations 集合
    ZsetOperations 有序集合
    HashOperations hashmap
     */
    @Resource(name = "redisTemplate")
    private ValueOperations<Object, Object> valueOperations;

    public void saveCustomer(Customer customer) {
        valueOperations.set(customer.getId(), customer);
    }

    public Map<Object, Object> getCustomer() {
        return (Map<Object, Object>) valueOperations.get("Customer001");
    }
}
