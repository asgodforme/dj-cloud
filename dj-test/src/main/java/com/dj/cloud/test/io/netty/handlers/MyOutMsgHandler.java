package com.dj.cloud.test.io.netty.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class MyOutMsgHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        System.out.println("outmsgread:");
        ctx.writeAndFlush("ChannelOutboundHandlerAdapter.read 发来一条消息\r\n");
        super.read(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("outmsgwrite:" + new String((byte[])msg));
        ctx.writeAndFlush("ChannelOutboundHandlerAdapter.write 发来一条消息\r\n");
        super.write(ctx, msg, promise);
    }
}
