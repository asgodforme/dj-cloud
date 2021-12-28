package com.dj.cloud.test.io.file.client;

import com.dj.cloud.test.io.file.domain.FileTransferProtocol;
import com.dj.cloud.test.io.file.util.MsgUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.File;
import java.io.IOException;

public class NettyClient {

    private static final EventLoopGroup workerGroup = new NioEventLoopGroup();

    public static void main(String[] args) throws InterruptedException, IOException {
        NettyClient nettyClient = new NettyClient();
        ChannelFuture f = nettyClient.connect("127.0.0.1", 7397);
        //文件信息{文件大于1024kb方便测试断点续传}
        File file = new File("C:\\test.txt");
        FileTransferProtocol fileTransferProtocol = MsgUtil.buildRequestTransferFile(file.getAbsolutePath(), file.getName(), file.length());
        System.out.println("aaaa");
        //发送信息；请求传输文件
        System.out.println(f.channel().isActive());
        f.channel().writeAndFlush(fileTransferProtocol);
        f.channel().closeFuture().sync();
    }

    private ChannelFuture connect(String inetHost, int inetPort) {
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ChannelFuture f = null;
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.AUTO_READ, true);
            b.handler(new MyChannelInitializer());
            f = b.connect(inetHost, inetPort).sync();
            System.out.println("itstack-demo-netty client start done. {关注公众号：bugstack虫洞栈，获取源码}");
//            //文件信息{文件大于1024kb方便测试断点续传}
//            File file = new File("C:\\test.txt");
//            FileTransferProtocol fileTransferProtocol = MsgUtil.buildRequestTransferFile(file.getAbsolutePath(), file.getName(), file.length());
//            System.out.println("aaaa");
//            //发送信息；请求传输文件
//            f.channel().writeAndFlush(fileTransferProtocol);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            workerGroup.shutdownGracefully();
        }
        return f;
    }
}
