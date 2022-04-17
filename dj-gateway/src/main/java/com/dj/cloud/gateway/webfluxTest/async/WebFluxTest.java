package com.dj.cloud.gateway.webfluxTest.async;

import com.dj.cloud.gateway.webfluxTest.pojo.Product;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

public class WebFluxTest {

    public static void main(String[] args) throws InterruptedException {
        WebClient webClient = WebClient.builder().build();
        webClient.get()
                .uri("http://localhost:10011/redisReactive/findAllProduct")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Product.class)
                .subscribe(System.out::println);

        CountDownLatch latch = new CountDownLatch(1);
        latch.await();

    }
}
