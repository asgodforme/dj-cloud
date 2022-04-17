package com.dj.cloud.gateway.webfluxTest.controller;

import com.dj.cloud.gateway.webfluxTest.pojo.Product;
import com.dj.cloud.gateway.webfluxTest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
webflux注解式的响应式： ServerHttpRequest, ServerHttpResponse
Springmvc: HttpServletRequest, HttpServletResponse.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/getProducts", produces = "text/event-stream")
    public Flux<Product> getProducts() {
        return this.productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable("id") final String id) {
        return this.productService.getProductById(id);
    }

    @PostMapping("")
    public Mono<Void> creatProduct(@RequestBody final Mono<Product> product) {
        return this.productService.createOrUpdateProduct(product);
    }

    @DeleteMapping("/{id}")
    public Mono<Product> delete(@PathVariable("id") final String id) {
        return this.productService.deleteProduct(id);
    }

}
