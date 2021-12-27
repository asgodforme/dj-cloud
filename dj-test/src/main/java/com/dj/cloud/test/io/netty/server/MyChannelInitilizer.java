package com.dj.cloud.test.io.netty.server;

import com.dj.cloud.test.io.netty.codec.MyDecoder;
import com.dj.cloud.test.io.netty.codec.MyEncoder;
import com.dj.cloud.test.io.netty.handlers.MyInMsgHandler;
import com.dj.cloud.test.io.netty.handlers.MyOutMsgHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

public class MyChannelInitilizer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
//        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
//        ch.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
//        ch.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
//        ch.pipeline().addLast(new MyChannelHandler4String());

//        ch.pipeline().addLast(new MyDecoder());
//        ch.pipeline().addLast(new MyEncoder());
//        ch.pipeline().addLast(new MyChannelHandler4String());

        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
        ch.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
        ch.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
        ch.pipeline().addLast(new MyInMsgHandler());
        ch.pipeline().addLast(new MyChannelHandler4String());
        ch.pipeline().addLast(new MyOutMsgHandler());
    }
}
