package com.dj.hello.client;

import com.dj.hello.HelloServiceGrpc;
import com.dj.hello.Request;
import com.dj.hello.Response;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.dj.hello.HelloServiceGrpc.HelloServiceBlockingStub;
import static com.dj.hello.HelloServiceGrpc.HelloServiceStub;

public class HelloClient {

    private final HelloServiceStub asyncStub;
    private final HelloServiceBlockingStub blockingStub;

    public HelloClient(Channel channel) {
        asyncStub = HelloServiceGrpc.newStub(channel);
        blockingStub = HelloServiceGrpc.newBlockingStub(channel);
    }

    public void hello() {
        Request request = Request.newBuilder().setMessage("我是hello请求者").build();
        for (byte b : request.toByteArray()) {
            System.out.println(b);

        }
        Response rep = blockingStub.hello(Request.newBuilder().setMessage("我是hello请求者").build());
        System.out.println("hello 返回: " + rep.getMessage());
    }

    public void helloStream() {
        Iterator<Response> responseIterator = blockingStub.helloStream(Request.newBuilder().setMessage("我要返回多条数据~").build());
        while (responseIterator.hasNext()) {
            System.out.println(responseIterator.next().getMessage());
        }
    }

    public void helloSingleStream() throws InterruptedException {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<Response> rspObser = new StreamObserver<Response>() {
            @Override
            public void onNext(Response value) {
                System.out.println("resposne: " + value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                finishLatch.countDown();
            }
        };
        StreamObserver<Request> reqObser = asyncStub.helloSingleStream(rspObser);
        try {
            for (int i = 0; i < 10; i++) {
                reqObser.onNext(Request.newBuilder().setMessage(i + "次请求helloSingleStream").build());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (finishLatch.getCount() == 0) {
                    return;
                }
            }
        } catch (RuntimeException e) {
            reqObser.onError(e);
            throw e;
        }
        reqObser.onCompleted();
        if (!finishLatch.await(1, TimeUnit.MINUTES)) {
            System.out.println("helloSingleStream can not finish within 1 minutes");
        }
    }

    public CountDownLatch helloDoubleStream() throws InterruptedException {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<Response> rspObser = new StreamObserver<Response>() {
            @Override
            public void onNext(Response value) {
                System.out.println("resposne: " + value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                finishLatch.countDown();
            }
        };
        StreamObserver<Request> reqObser = asyncStub.helloDoubleStream(rspObser);
        try {
            for (int i = 0; i < 10; i++) {
                reqObser.onNext(Request.newBuilder().setMessage(i + "helloDoubleStream").build());
            }
        } catch (RuntimeException e) {
            reqObser.onError(e);
            throw e;
        }
        reqObser.onCompleted();
        return finishLatch;
    }

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel managedChannel = ManagedChannelBuilder.forTarget("localhost:8080").usePlaintext().build();
        HelloClient helloClient = new HelloClient(managedChannel);
        helloClient.hello();
//        helloClient.helloStream();
//        helloClient.helloSingleStream();
//        CountDownLatch finishLatch = helloClient.helloDoubleStream();
//        finishLatch.await();

    }
}
