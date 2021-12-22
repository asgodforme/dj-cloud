package com.dj.cloud.test.io.aio.server;


import com.dj.cloud.test.io.aio.ChannelInitializer;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class AioServerChannelInitializer extends ChannelInitializer {
    @Override
    public void initChannel(AsynchronousSocketChannel channel) throws Exception {
        System.out.println("服务端初始化通道：" + channel);
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
//        channel.read(readBuffer, 10, TimeUnit.SECONDS, null,
//                new AioServerHandler(channel, Charset.forName("GBK")));
        channel.read(readBuffer, readBuffer, new AioServerHandler(channel, Charset.forName("GBK")));
    }
}
