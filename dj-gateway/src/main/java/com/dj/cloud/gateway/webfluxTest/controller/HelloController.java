package com.dj.cloud.gateway.webfluxTest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    @RequestMapping("/")
    public Mono<String> index() {
        return Mono.just("Hello Spring boot");
    }
}
