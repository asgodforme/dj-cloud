package com.dj.cloud.test.netty.msgpack;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoServerHandler extends SimpleChannelInboundHandler<Object> {

    private int counter = 0;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端接受："+msg);
        ctx.writeAndFlush(msg);
    }
}
