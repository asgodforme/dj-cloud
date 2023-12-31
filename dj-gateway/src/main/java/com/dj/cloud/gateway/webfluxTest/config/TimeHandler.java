package com.dj.cloud.gateway.webfluxTest.config;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@Component
public class TimeHandler {

    public Mono<ServerResponse> sendTimePerSec(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(
                        Flux.interval(Duration.ofSeconds(1))
                                .map(l -> new SimpleDateFormat("HH-mm-ss").format(new Date())), String.class);

    }

    public Mono<ServerResponse> sendTimePerSec() {
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(
                        Flux.interval(Duration.ofSeconds(1))
                                .map(l -> new SimpleDateFormat("HH-mm-ss").format(new Date())), String.class);

    }
}
