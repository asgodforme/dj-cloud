package com.dj.cloud.gateway.webfluxTest.routers;

import com.dj.cloud.gateway.webfluxTest.handlers.HelloHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HelloRouter {

    @Bean
    public RouterFunction<ServerResponse> routeHello(HelloHandler helloHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/helloo")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), helloHandler::hello);
    }
}
