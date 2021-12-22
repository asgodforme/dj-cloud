package com.dj.cloud.test.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

public class ChannelHandler {

    AsynchronousSocketChannel channel;
    Charset charset;


    public ChannelHandler(AsynchronousSocketChannel channel, Charset charset) {
        this.channel = channel;
        this.charset = charset;
    }

    public void writeAndFlush(Object msg) {
        byte[] bytes = msg.toString().getBytes(charset);
//        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
//        byteBuffer.put(bytes);
//        byteBuffer.flip();
        channel.write(ByteBuffer.wrap(bytes));
    }

    public AsynchronousSocketChannel channel() {
        return channel;
    }
}
