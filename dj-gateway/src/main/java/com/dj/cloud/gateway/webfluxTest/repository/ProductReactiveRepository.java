package com.dj.cloud.gateway.webfluxTest.repository;

import com.dj.cloud.gateway.webfluxTest.config.RedisReactiveConfig;
import com.dj.cloud.gateway.webfluxTest.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@ConditionalOnBean(RedisReactiveConfig.class)
public class ProductReactiveRepository implements ProductRactiveRepository{

    @Autowired
    private ReactiveRedisTemplate<String, Product> reactiveRedisTemplate;

    private static final String HASH_NAME = "PRODUCT:";

    @Override
    public Mono<Boolean> saveProduct(Product product) {
        return reactiveRedisTemplate.opsForValue().set(HASH_NAME + product.getId(), product);
    }

    @Override
    public Mono<Boolean> updateProduct(Product product) {
        return reactiveRedisTemplate.opsForValue().set(HASH_NAME + product.getId(), product);
    }

    @Override
    public Mono<Boolean> deleteProduct(String productId) {
        return reactiveRedisTemplate.opsForValue().delete(productId);
    }

    @Override
    public Mono<Product> findProductById(String productId) {
        return reactiveRedisTemplate.opsForValue().get(productId);
    }

    @Override
    public Flux<Product> findAllProduct() {
        return reactiveRedisTemplate.keys(HASH_NAME + "*")
                .flatMap(key -> reactiveRedisTemplate.opsForValue().get(key));
    }
}
