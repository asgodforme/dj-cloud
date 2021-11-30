package com.dj.cloud.test.netty.question1ans;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class TimeServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int counter;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        byte[] req = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(req);
//        String body = new String(req, "UTF-8")
//                .substring(0, req.length - System.getProperty("line.separator").length());
        String body = new String(req, "UTF-8");
        System.out.println("服务器接收到：" + body + " 次数：" + ++counter);

        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                LocalDateTime.now().toString() :
                "BAD ORDER";

        currentTime = currentTime + System.getProperty("line.separator");

        ByteBuf resp = Unpooled.wrappedBuffer(currentTime.getBytes(StandardCharsets.UTF_8));
        channelHandlerContext.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
