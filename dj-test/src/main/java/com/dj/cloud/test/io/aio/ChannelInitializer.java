package com.dj.cloud.test.io.aio;

import com.dj.cloud.test.io.aio.server.AioServer;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public abstract class ChannelInitializer implements CompletionHandler<AsynchronousSocketChannel, AioServer> {

    public abstract void initChannel(AsynchronousSocketChannel channel) throws Exception;

    @Override
    public void completed(AsynchronousSocketChannel result, AioServer attachment) {
        try {
            attachment.serverSocketChannel().accept(attachment, this);
            System.out.println("接收到来自客户端的请求， 服务端为：" + attachment + ",channel: " + result);
            initChannel(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, AioServer attachment) {
        exc.printStackTrace();
    }
}
