package com.dj.cloud.test.io.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

public class MyClientChannelInitial extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.println("我连接上服务器了！：" + ch.id());
        // 基于换行符号
        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8
        ch.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8
        ch.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
        // 在管道中添加我们自己的接收数据实现方法
        ch.pipeline().addLast(new MyClientHandler());
    }
}
