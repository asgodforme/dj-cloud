package mq.quickstart.nettyctmq.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import mq.quickstart.nettyctmq.codec.mashaller.ConnectionStartMashaller;
import mq.quickstart.nettyctmq.domain.RabbitMQFrame;

import java.util.List;

public class ByteToRabbitMQFrameDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("进入消息解码器（ByteBuf -> RabbitMQFrame）");
        ConnectionStartMashaller connectionStartMashaller = new ConnectionStartMashaller();
        RabbitMQFrame rabbitMQFrame = connectionStartMashaller.mashallerDo(in);
        out.add(rabbitMQFrame);
    }

}

