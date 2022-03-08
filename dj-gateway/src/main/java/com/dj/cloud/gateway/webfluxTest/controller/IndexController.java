package com.dj.cloud.gateway.webfluxTest.controller;

import com.dj.cloud.gateway.webfluxTest.config.TimeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@RestController
public class IndexController {

    @Autowired
    private TimeHandler timeHandler;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Welcome to react world~");
    }

    @GetMapping(value = "/time", produces = "text/event-stream")
    public Flux<String> time() {
        System.out.println("request time ");
        return Flux.interval(Duration.ofSeconds(1))
                .map(l -> new SimpleDateFormat("HH-mm-ss").format(new Date()));
    }
}
