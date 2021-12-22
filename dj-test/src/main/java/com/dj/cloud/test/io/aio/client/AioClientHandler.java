package com.dj.cloud.test.io.aio.client;

import com.dj.cloud.test.io.aio.ChannelAdapter;
import com.dj.cloud.test.io.aio.ChannelHandler;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

public class AioClientHandler extends ChannelAdapter {

    public AioClientHandler(AsynchronousSocketChannel socketChannel, Charset charset) {
        super(socketChannel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        try {
            System.out.println("已经连接上服务端：" + ctx.channel().getRemoteAddress());
            //通知客户端链接建立成功
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelInactive(ChannelHandler ctx) {

    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
//        System.out.println("微信公众号：bugstack虫洞栈 | 服务端收到：" + new Date() + " " + msg + "\r\n");
        ctx.writeAndFlush("客户端信息处理Success！\r\n");
    }


}
