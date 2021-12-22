package com.dj.cloud.test.io.aio.plus.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AsyncTimeServerHandler extends Thread {

        private AsynchronousServerSocketChannel asynchronousServerSocketChannel;
        private CountDownLatch countDownLatch;

        public AsyncTimeServerHandler(int port) {
            try {
                asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
                asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
                System.out.println("异步时间服务器已经启动");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            countDownLatch = new CountDownLatch(1);
            asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public AsynchronousServerSocketChannel asynchronousServerSocketChannel() {
            return asynchronousServerSocketChannel;
        }

        public CountDownLatch countDownLatch() {
            return countDownLatch;
        }

}
