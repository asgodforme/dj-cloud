package com.dj.cloud.test.io.bio.client;

import com.dj.cloud.test.io.bio.ChannelAdapter;
import com.dj.cloud.test.io.bio.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

public class BioClientHandler extends ChannelAdapter {

    public BioClientHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        System.out.println("客户端连接服务端成功！" + ctx.socket());
        ctx.writeAndFlush("您好！服务器，我是刚刚连接你的客户端");
    }

    @Override
    public void channelRead(ChannelHandler ctx, String msg) {
        System.out.println(LocalDateTime.now() + "接收到消息：" + msg);
        ctx.writeAndFlush("hi, 我已经接收到了你的消息");
    }

}
