package com.dj.cloud.test.io.bio.server;

import com.dj.cloud.test.io.bio.ChannelAdapter;
import com.dj.cloud.test.io.bio.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

public class BioServerHandler extends ChannelAdapter {

    public BioServerHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        System.out.println("服务器启动成功，本地端口信息:" + ctx.socket());
        ctx.writeAndFlush("hello, 我是服务端，发个消息给你");
    }

    @Override
    public void channelRead(ChannelHandler ctx, String msg) {
        System.out.println(LocalDateTime.now() + "接受到消息：" + msg);
        ctx.writeAndFlush("我是服务器，我已经接收到了你的消息");
    }
}
