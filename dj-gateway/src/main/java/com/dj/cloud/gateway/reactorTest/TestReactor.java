package com.dj.cloud.gateway.reactorTest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

public class TestReactor {
    public static void main(String[] args) throws InterruptedException {
//        Flux.just(1,2,3,4,5)
//                // concatMap: 先通过map进行转换，然后拼接成一个Flux<T>序列
//                .concatMap(a -> Mono.just(a).filterWhen(b -> Mono.just(b.intValue() % 2 == 0)))
//                .next()
//                // 取第一个元素
//                .subscribe(System.out::println);
//
//        System.out.println("--------------------------------");
//        Integer[] arr = {1, 2, 3, 4, 5};
//        Flux.fromArray(arr)
//                .concatMap(a -> Mono.just(a).filterWhen(b -> Mono.just(b % 2 == 0)))
//                .subscribe(System.out::println);
//
//        System.out.println("--------------------------------");
//        Flux.fromIterable(Arrays.asList(arr))
//                .concatMap(a -> Mono.just(a).filterWhen(b -> Mono.just(b % 2 != 0)))
//                .subscribe(System.out::println);
//
//        System.out.println("--------------------------------");
//        Flux.fromStream(Arrays.asList(arr).stream())
//                .concatMap(a -> Mono.just(a).filterWhen(b -> Mono.just(b % 2 != 0)))
//                .subscribe(System.out::println);


//        Flux.empty().subscribe(System.out::println);
//        Flux.error(new Exception()).subscribe(System.out::println);
//        Flux.never();
//        Flux.range(0, 10).subscribe(System.out::println);

//        Flux.interval(Duration.ofMillis(1000)).subscribe(System.out::println);
//        CountDownLatch latch = new CountDownLatch(1);
//        latch.await();


//        Flux.generate(sink -> {
//            sink.next("Hello");
//            sink.complete();
//        }).subscribe(System.out::println);

//        Flux.create(sink -> {
//            for (int i = 0; i < 10; i++) {
//                sink.next(i);
//            }
//            sink.complete();
//        }).subscribe(System.out::println);

//        Mono.delay(Duration.ofMillis(1000)).subscribe(System.out::println);
//        CountDownLatch latch = new CountDownLatch(1);
//        latch.await();

//        Mono.justOrEmpty(Optional.of("hello"))
//                .subscribe(System.out::println);

//        Flux.range(1, 50).buffer(10).subscribe(System.out::println);
//        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0)
//                .subscribe(System.out::println);
//        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);

        Flux.range(1, 5).flatMap(x -> Mono.just(x * x)).subscribe(System.out::println);
    }


}
