package com.dj.cloud.test.io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ChannelHandler {

    private SocketChannel socketChannel;
    private Charset charset;

    public ChannelHandler(SocketChannel socketChannel, Charset charset) {
        this.socketChannel = socketChannel;
        this.charset = charset;
    }

    public SocketChannel socketChannel() {
        return this.socketChannel;
    }

    public Charset charset() {
        return this.charset;
    }

    public void writeAndFlush(String s) {
        try {
            socketChannel.write(ByteBuffer.wrap(s.getBytes(charset)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
