package com.dj.cloud.gateway.webfluxTest.repository;


import com.dj.cloud.gateway.webfluxTest.pojo.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRactiveRepository {

    Mono<Boolean> saveProduct(Product product);

    Mono<Boolean> updateProduct(Product product);

    Mono<Boolean> deleteProduct(String productId);

    Mono<Product> findProductById(String productId);

    Flux<Product> findAllProduct();

}
