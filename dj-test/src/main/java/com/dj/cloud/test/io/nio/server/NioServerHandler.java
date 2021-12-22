package com.dj.cloud.test.io.nio.server;

import com.dj.cloud.test.io.nio.ChannelAdapter;
import com.dj.cloud.test.io.nio.ChannelHandler;

import java.nio.channels.Selector;
import java.nio.charset.Charset;

public class NioServerHandler extends ChannelAdapter {
    public NioServerHandler(Selector selector, Charset charset) {
        super(selector, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        System.out.println("服务器接收到连接请求：" + ctx.socketChannel());
        ctx.writeAndFlush("hi , i am server");
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println("server recevie: " + msg);
        ctx.writeAndFlush("server receive u send msg");

    }
}
