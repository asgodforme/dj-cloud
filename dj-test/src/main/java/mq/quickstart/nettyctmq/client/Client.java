package mq.quickstart.nettyctmq.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import mq.quickstart.nettyctmq.codec.ByteToRabbitMQFrameDecoder;
import mq.quickstart.nettyctmq.handler.MqHandler;
import mq.quickstart.nettyctmq.handler.MqOutHandler;

public class Client {

    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.AUTO_READ, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ByteToRabbitMQFrameDecoder());
                    ch.pipeline().addLast(new MqHandler());
                    ch.pipeline().addLast(new MqOutHandler());
                }
            });
            ChannelFuture f = b.connect("192.168.13.129", 5672).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
