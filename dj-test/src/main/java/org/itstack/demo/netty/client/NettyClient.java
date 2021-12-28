package org.itstack.demo.netty.client;

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

/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：bugstack虫洞栈  ｛获取学习源码｝
 * 虫洞群：5360692
 * Create by fuzhengwei on @2019
 */
public class NettyClient {

    public static void main(String[] args) {
        //启动客户端
        ChannelFuture channelFuture = new NettyClient().connect("127.0.0.1", 8080);

        //文件信息{文件大于1024kb方便测试断点续传}
        File file = new File("C:\\test.txt");
        FileTransferProtocol fileTransferProtocol = MsgUtil.buildRequestTransferFile(file.getAbsolutePath(), file.getName(), file.length());

        //发送信息；请求传输文件
        channelFuture.channel().writeAndFlush(fileTransferProtocol);
    }

    //配置服务端NIO线程组
    private EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture connect(String inetHost, int inetPort) {
        ChannelFuture channelFuture = null;
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.AUTO_READ, true);
            b.handler(new MyChannelInitializer());
            channelFuture = b.connect(inetHost, inetPort).syncUninterruptibly();
            this.channel = channelFuture.channel();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != channelFuture && channelFuture.isSuccess()) {
                System.out.println("itstack-demo-netty client start done. {关注公众号：bugstack虫洞栈，获取源码}");
            } else {
                System.out.println("itstack-demo-netty client start error. {关注公众号：bugstack虫洞栈，获取源码}");
            }
        }
        return channelFuture;
    }

    public void destroy() {
        if (null == channel) return;
        channel.close();
        workerGroup.shutdownGracefully();
    }

}
