package com.dj.cloud.test.netty.question1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class TimeClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int counter;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String body = new String(bytes, "UTF-8");
        System.out.println("客户端收到返回：" + body + " 次数： " + ++counter);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接已经建立");
//        String resp = "QUERY TIME ORDER" + System.getProperty("line.separator");
        String resp = "QUERY TIME ORDER";
        for (int i = 0; i < 100; i++) {
            TimeUnit.SECONDS.sleep(1);
            ByteBuf byteBuf = Unpooled.wrappedBuffer(resp.getBytes(StandardCharsets.UTF_8));
            ctx.writeAndFlush(byteBuf);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("客户端发生异常" + cause.getMessage());
        ctx.close();
    }
}
