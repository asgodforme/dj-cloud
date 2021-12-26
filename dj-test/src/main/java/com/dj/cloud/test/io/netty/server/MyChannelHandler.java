package com.dj.cloud.test.io.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

public class MyChannelHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] msgBytes = new byte[msg.readableBytes()];
        msg.readBytes(msgBytes);
        System.out.println("服务端收到消息：" + new String(msgBytes));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端收到连接：" + ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }
}
