package com.dj.cloud.test.io.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.Charset;

public class NioServer {
    private Selector selector;
    private ServerSocketChannel socketChannel;

    public void bind(int port) throws IOException {
        selector = Selector.open();
        socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.socket().bind(new InetSocketAddress(port), 1024);
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端已经准备好");
        new NioServerHandler(selector, Charset.forName("utf-8")).start();
    }

    public static void main(String[] args) throws IOException {
        new NioServer().bind(8080);
    }
}
