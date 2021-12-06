package com.dj.cloud.test.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpFileServer {

    private static final String DEFAULT_URL = "src/main/java/com/dj/cloud/test/netty/http";

    public void run(final int port, final String url) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                            /**
                             * 将多个消息转换为单一的FullHttpRequest或者FullHttpResponse.原因是HTTP解码器在每个HTTP消息中会生成多个消息对象
                             * 1.HttpRequset/HttpResponse
                             * 2. HttpContent
                             * 3. LastHttpContent
                             */
                            ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            ch.pipeline().addLast("htt-encoder", new HttpResponseEncoder());
                            /**
                             * 支持异步发送给大的码流。但不占用过多的内存，防止发生java内存溢出错误。
                             */
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            ch.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(DEFAULT_URL));
                        }
                    });

            ChannelFuture f = serverBootstrap.bind(url, port).sync();
            System.out.println("HTTP文件服务器启动成功！");
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new HttpFileServer().run(8080, "127.0.0.1");
    }
}
