package com.dj.cloud.gateway.webfluxTest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {

//    @Bean
//    public WebHandler webHandler(ApplicationContext applicationContext) {
//        DispatcherHandler dispatcherHandler = new DispatcherHandler(applicationContext);
//        return dispatcherHandler;
//    }

    @Autowired
    private TimeHandler timeHandler;

    @Bean
    public RouterFunction<ServerResponse> timeRouter() {
        return RouterFunctions.route(RequestPredicates.GET("/timesReactive"), timeHandler::sendTimePerSec);
    }
}
