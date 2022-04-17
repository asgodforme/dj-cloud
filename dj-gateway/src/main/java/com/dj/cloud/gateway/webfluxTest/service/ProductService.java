package com.dj.cloud.gateway.webfluxTest.service;

import com.dj.cloud.gateway.webfluxTest.pojo.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductService {

    private final Map<String, Product> products = new ConcurrentHashMap<>();

    public Flux<Product> getProducts() {
        return Flux.fromIterable(this.products.values());
    }

    public Flux<Product> getProductsByIds(final Flux<String> ids) {
        return ids.flatMap(id -> Mono.justOrEmpty(products.get(id)));
    }

    public Mono<Product> getProductById(final String id) {
        return Mono.justOrEmpty(this.products.get(id));
    }

    public Mono<Void> createOrUpdateProduct(final Mono<Product> productMono) {
        return productMono.doOnNext(product -> {
            products.put(product.getId(), product);
        }).thenEmpty(Mono.empty());
    }

    public Mono<Product> deleteProduct(final String id) {
        return Mono.justOrEmpty(this.products.remove(id));
    }
}
