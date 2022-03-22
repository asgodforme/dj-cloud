package com.dj.hello.server;

import com.dj.hello.HelloServiceGrpc;
import com.dj.hello.Request;
import com.dj.hello.Response;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HelloServer {

    private final int port;
    private final Server server;

    public HelloServer(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port).addService(new HelloService()).build();
    }

    public void start() throws IOException {
        server.start();
        System.out.println("server start on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    HelloServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static class HelloService extends HelloServiceGrpc.HelloServiceImplBase {
        @Override
        public void hello(Request request, StreamObserver<Response> responseObserver) {
            System.out.println("request: " + request.getMessage());
            String msg = "当前接口：hello, 请求数据：" + request.getMessage() + "返回信息: 您好！当前时间是" + new Date();
            responseObserver.onNext(Response.newBuilder().setMessage(msg).build());
            responseObserver.onCompleted();
        }

        @Override
        public void helloStream(Request request, StreamObserver<Response> responseObserver) {
            for (int i = 0; i < 10; i++) {
                String msg = "当前接口：helloStream, 请求数据：" + request.getMessage() + " 返回信息: 您好！当前时间是" + new Date();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                responseObserver.onNext(Response.newBuilder().setMessage(msg).build());
            }
            responseObserver.onCompleted();
        }

        @Override
        public StreamObserver<Request> helloSingleStream(StreamObserver<Response> responseObserver) {
            return new StreamObserver<Request>() {
                final List<Request> requests = new ArrayList<>();
                final long startTime = System.currentTimeMillis();
                @Override
                public void onNext(Request request) {
                    requests.add(request);
                }

                @Override
                public void onError(Throwable throwable) {
                    System.err.println("helloSingleStream发生异常：" + throwable);
                }

                @Override
                public void onCompleted() {
                    long spent = (System.currentTimeMillis() - startTime) / 1000;
                    responseObserver.onNext(Response.newBuilder().setMessage("一共收到了" + requests.size() +"条数据, 用时" + spent + "s" ).build());
                    responseObserver.onCompleted();
                }
            };
        }

        @Override
        public StreamObserver<Request> helloDoubleStream(StreamObserver<Response> responseObserver) {
            return new StreamObserver<Request>() {

                int count = 0;

                @Override
                public void onNext(Request request) {
                    responseObserver.onNext(Response.newBuilder().setMessage(new Date() + "收到了请求信息" + request).build());
                    count++;
                }

                @Override
                public void onError(Throwable throwable) {
                    System.err.println("helloDoubleStream发生异常：" + throwable);
                }

                @Override
                public void onCompleted() {
                    responseObserver.onNext(Response.newBuilder().setMessage("一共收到了" + count +"条数据").build());
                    responseObserver.onCompleted();
                }
            };
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        HelloServer helloServer = new HelloServer(8080);
        helloServer.start();
        helloServer.blockUntilShutdown();

    }
}
