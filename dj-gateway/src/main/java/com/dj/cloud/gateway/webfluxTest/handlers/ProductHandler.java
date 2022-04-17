package com.dj.cloud.gateway.webfluxTest.handlers;

import com.dj.cloud.gateway.webfluxTest.pojo.Product;
import com.dj.cloud.gateway.webfluxTest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/*
    HandlerFunction
 */
@Component
public class ProductHandler {

    @Autowired
    private ProductService productService;

    public Mono<ServerResponse> getProducts(ServerRequest request) {
        return ServerResponse.ok()
//                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(this.productService.getProducts(), Product.class);
    }

    public Mono<ServerResponse> getProductById(ServerRequest request) {
        String id = request.pathVariable("id");
        return  ServerResponse.ok()
                .body(this.productService.getProductById(id), Product.class);
    }

    public Mono<ServerResponse> createProduct(ServerRequest request) {
        Mono<Product> product = request.bodyToMono(Product.class);
        return ServerResponse.ok().body(this.productService.createOrUpdateProduct(product), Product.class);
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok().body(this.productService.deleteProduct(id), Product.class);
    }
}
