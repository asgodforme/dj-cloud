package com.dj.cloud.test.io.aio.server;

import com.dj.cloud.test.io.aio.ChannelAdapter;
import com.dj.cloud.test.io.aio.ChannelHandler;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

public class AioServerHandler extends ChannelAdapter {

    public AioServerHandler(AsynchronousSocketChannel channel, Charset charset) {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try {
            System.out.println(ctx.channel().getRemoteAddress() + "已经连接上服务器");
//            ctx.writeAndFlush("客户端你好！当前消息是：" + " " + new Date() + " " + ctx.channel().getRemoteAddress() + "\r\n");
            ctx.writeAndFlush("12345");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelInactive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        ctx.writeAndFlush("服务端信息处理Success！\r\n");
    }
}
