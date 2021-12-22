package com.dj.cloud.test.io.nio.client;

import com.dj.cloud.test.io.nio.ChannelAdapter;
import com.dj.cloud.test.io.nio.ChannelHandler;

import java.nio.channels.Selector;
import java.nio.charset.Charset;

public class NioClientHandler extends ChannelAdapter {
    public NioClientHandler(Selector selector, Charset charset) {
        super(selector, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        System.out.println("客户端已经激活连接：" + ctx.socketChannel());
        ctx.writeAndFlush("hello, i am client");
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println("客户端收到：" + msg);
        ctx.writeAndFlush("client receive u message.");
    }
}
