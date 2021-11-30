package com.dj.cloud.test.netty.delimiter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.StandardCharsets;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int counter = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        String body = new String(bytes, "UTF-8");
        System.out.println(++counter + "客户端接收到服务器消息：" + body);
    }

    private static final String ECHO = "您好！姜杰大神$_";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO.getBytes(StandardCharsets.UTF_8)));
        }
    }
}
