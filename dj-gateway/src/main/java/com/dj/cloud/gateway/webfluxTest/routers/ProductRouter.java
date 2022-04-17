package com.dj.cloud.gateway.webfluxTest.routers;

import com.dj.cloud.gateway.webfluxTest.handlers.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Router路由信息
 */
@Configuration
public class ProductRouter {

    @Bean
    public RouterFunction<ServerResponse> routeProduct(ProductHandler productHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/products").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), productHandler::getProducts)
                .andRoute(RequestPredicates.GET("/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), productHandler::getProductById)
                .andRoute(RequestPredicates.POST("/").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), productHandler::createProduct)
                .andRoute(RequestPredicates.DELETE("/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), productHandler::deleteProduct);
    }
}
