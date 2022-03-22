package com.dj.cloud.gateway.reactorTest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TestReactor {
    public static void main(String[] args) {
        Flux.just(1,2,3,4,5)
                // concatMap: 先通过map进行转换，然后拼接成一个Flux<T>序列
                .concatMap(a -> Mono.just(a).filterWhen(b -> Mono.just(b.intValue() % 2 == 0)))
                .next()
                // 取第一个元素
                .subscribe(System.out::println);
    }
}
