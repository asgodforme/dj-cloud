package com.dj.cloud.facility.netty.server;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

/**
 * netty ChannelHandler初始化
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 基于换行符
        pipeline.addLast(new LineBasedFrameDecoder(1024));
        // 解码转String
        pipeline.addLast(new StringDecoder(StandardCharsets.UTF_8));
        // 编码转字节流
        pipeline.addLast(new StringEncoder(StandardCharsets.UTF_8));
        // 自定义处理逻辑
        pipeline.addLast(new MyServerHandler());
    }
}
