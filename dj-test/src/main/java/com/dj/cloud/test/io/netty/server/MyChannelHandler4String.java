package com.dj.cloud.test.io.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

public class MyChannelHandler4String extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务端收到消息：" + msg);
//        ChannelHandler.channelGroup.writeAndFlush(msg +  "\r\n");
//        ChannelHandler.channelGroup.writeAndFlush(msg.getBytes(StandardCharsets.UTF_8));
            ctx.channel().writeAndFlush(msg.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端收到连接：" + ctx.channel().remoteAddress());
        String resp = "我是服务器，您好哦！" + "\r\n";
        ByteBuf respBuf = Unpooled.buffer(resp.getBytes(StandardCharsets.UTF_8).length);
        respBuf.writeBytes(resp.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(respBuf);
        ChannelHandler.channelGroup.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "已断开连接");
        ChannelHandler.channelGroup.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        System.out.println("异常信息：" + cause.getMessage());
    }
}
