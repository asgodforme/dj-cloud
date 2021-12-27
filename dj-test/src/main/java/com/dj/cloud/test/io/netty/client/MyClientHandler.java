package com.dj.cloud.test.io.netty.client;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //通知客户端链消息发送成功
        String str = "客户端收到：" + new Date() + " " + msg + "\r\n";
        System.out.println("客户端收到："+ str);
        ctx.writeAndFlush(str);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String msg = "11111";
        //接收msg消息{与上一章节相比，此处已经不需要自己进行解码}
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);
        //通知客户端链消息发送成功
        String str = "客户端收到：" + new Date() + " " + msg + "\r\n";
        ctx.writeAndFlush(str);
    }
}
