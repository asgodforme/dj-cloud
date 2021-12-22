package com.dj.cloud.test.io.aio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AioClient {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        // 打开客户端通道
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 连接到目标服务器，并返回预期对象Future
        Future<Void> future = socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
        System.out.println("客户端连接成功");
        future.get();
        // 拿到服务器返回的数据

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        socketChannel.read(byteBuffer, null, new AioClientHandler(socketChannel, Charset.forName("GBK")));
        countDownLatch.await();
    }


}
