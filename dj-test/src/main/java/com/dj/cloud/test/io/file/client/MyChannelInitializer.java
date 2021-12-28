package com.dj.cloud.test.io.file.client;

import com.dj.cloud.test.io.file.codec.ObjDecoder;
import com.dj.cloud.test.io.file.codec.ObjEncoder;
import com.dj.cloud.test.io.file.domain.FileTransferProtocol;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel channel) throws Exception {
            //对象传输处理
            channel.pipeline().addLast(new ObjDecoder(FileTransferProtocol.class));
            channel.pipeline().addLast(new ObjEncoder(FileTransferProtocol.class));
            // 在管道中添加我们自己的接收数据实现方法
            channel.pipeline().addLast(new MyClientHandler());
        }
}
