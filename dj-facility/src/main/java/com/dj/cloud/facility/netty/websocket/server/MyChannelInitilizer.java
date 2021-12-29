package com.dj.cloud.facility.netty.websocket.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyChannelInitilizer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast("http-codec", new HttpServerCodec());
        channelPipeline.addLast("aggregator", new HttpObjectAggregator(65536));
        channelPipeline.addLast("http-chunked", new ChunkedWriteHandler());

        channelPipeline.addLast(new MyServerHandler());
    }
}
