package mq.quickstart.nettyctmq.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import mq.quickstart.nettyctmq.codec.mashaller.*;
import mq.quickstart.nettyctmq.domain.HeartbeatFrame;
import mq.quickstart.nettyctmq.domain.RabbitMQFrame;

import java.util.List;

public class ByteToRabbitMQFrameDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("进入消息解码器（ByteBuf -> RabbitMQFrame）");
        byte b = in.readByte();
        if (b == 8) {
            System.out.println("收到心跳包了！！！！！！！！");
            RabbitMQFrame rabbitMQFrame = new RabbitMQFrame();
            rabbitMQFrame.setFrameType(b);
            rabbitMQFrame.setPayload(new HeartbeatFrame());
            // 跳过字节信息，让netty自动ack确认请求
            in.skipBytes(in.readableBytes());
            out.add(rabbitMQFrame);
        } else {
            in.resetReaderIndex();
            in.readBytes(7);
            short classId = in.readShort();
            short methodId = in.readShort();
            System.out.println("classId: " + classId + ", methodId: " + methodId);
            Mashaller mashaller;
            if (classId == 10 && methodId == 10) {
                mashaller = new ConnectionStartMashaller();
            } else if (classId == 10 && methodId == 30) {
                mashaller = new ConnectionTuneMashaller();
            } else if (classId == 10 && methodId == 41) {
                mashaller = new ConnectionOpenMashaller();
            } else if (classId == 20 && methodId == 11) {
                mashaller = new ChannelOpenMashaller();
            }else {
                return;
            }
            RabbitMQFrame rabbitMQFrame = mashaller.mashallerDo(in);
            out.add(rabbitMQFrame);
        }
    }

}

