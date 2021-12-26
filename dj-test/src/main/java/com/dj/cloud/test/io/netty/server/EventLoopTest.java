package com.dj.cloud.test.io.netty.server;

import io.netty.channel.DefaultEventLoop;
import io.netty.channel.EventLoop;
import io.netty.util.concurrent.Future;

import java.util.concurrent.ExecutionException;

public class EventLoopTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EventLoop eventLoop = new DefaultEventLoop();
        eventLoop.execute(() -> System.out.println("1111"));
        Future<?> sync = eventLoop.shutdownGracefully().sync();
        System.out.println(sync.get());
    }
}
