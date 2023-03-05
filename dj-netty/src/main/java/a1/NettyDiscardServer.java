package a1;

import a1.handler.NettyDiscardHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyDiscardServer {
    private  final int serverPort;
    ServerBootstrap b = new ServerBootstrap();

    public NettyDiscardServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public void runServer() {
        // 负责服务器通道新连接的IO事件的监听  ----》包工头
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        // 负责传输通道的IO事件的处理和数据传输 ----》  工人
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            b.group(bossLoopGroup, workerLoopGroup);
            b.channel(NioServerSocketChannel.class);
            b.localAddress(serverPort);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyDiscardHandler());
                }
            });
            ChannelFuture channelFuture = b.bind().sync();
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();

            closeFuture.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerLoopGroup.shutdownGracefully();
            bossLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyDiscardServer(8080).runServer();
    }
}
