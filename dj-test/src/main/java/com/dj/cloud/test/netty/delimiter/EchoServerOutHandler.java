package com.dj.cloud.test.netty.delimiter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class EchoServerOutHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        super.read(ctx);
        System.out.println("EchoServerOutHandler.read calls");
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
        System.out.println("EchoServerOutHandler.write calls");
    }


}
