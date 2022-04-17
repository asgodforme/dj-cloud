package com.dj.cloud.gateway.webfluxTest.controller;

import com.dj.cloud.gateway.webfluxTest.config.RedisReactiveConfig;
import com.dj.cloud.gateway.webfluxTest.pojo.Product;
import com.dj.cloud.gateway.webfluxTest.repository.ProductRactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("/redisReactive")
@ConditionalOnBean(RedisReactiveConfig.class)
public class RedisReactiveController {

    @Autowired
    private ProductRactiveRepository productRactiveRepository;

    @GetMapping("/saveProduct")
    public Mono<Boolean> saveProduct() {
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setProductName("测试产品001");
        product.setProductCode("test001");
        product.setDescription("测试山品");
        product.setPrice(100.0f);
        return productRactiveRepository.saveProduct(product);
    }

    @GetMapping(value = "/findAllProduct")
//            produces = "text/event-stream")
    public Flux<Product> findAllProduct() {
        return productRactiveRepository.findAllProduct();
//                .delayElements(Duration.ofSeconds(2));
    }
}
