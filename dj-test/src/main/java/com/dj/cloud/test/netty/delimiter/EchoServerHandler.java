package com.dj.cloud.test.netty.delimiter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.StandardCharsets;

public class EchoServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int counter = 0;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        String body = new String(bytes, "UTF-8");
        System.out.println("这是第"+ ++counter + "次接收客户端的数据：" + body);
        body = body + "$_";
        ByteBuf echo = Unpooled.copiedBuffer(body.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(echo);
    }
}
