package mq.quickstart.nettyctmq.codec.mashaller;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import mq.quickstart.nettyctmq.domain.MethodFrame;
import mq.quickstart.nettyctmq.domain.RabbitMQFrame;

import java.util.Map;

public class ConnectionTuneMashaller extends AbstractMashaller {

    @Override
    protected void mashallerArguments(ByteBuf in, Map<String, Object> arguments) {
        short channelMax = in.readShort();
        arguments.put("channelMax", channelMax);
        int frameMax = in.readInt();
        arguments.put("frameMax", frameMax);
        short heartbeat = in.readShort();
        arguments.put("heartbeat", heartbeat);
    }

    @Override
    protected void marshallerOkArgument(RabbitMQFrame rabbitMQFrame, ByteBuf respByteBuf) {
        MethodFrame methodFrame = (MethodFrame) rabbitMQFrame.getPayload();
        Map<String, Object> arguments = methodFrame.getArguments();
        ByteBuf payload = ByteBufAllocator.DEFAULT.buffer();

        payload.writeShort(10).writeShort(31);
        payload.writeShort((short) arguments.get("channelMax"));
        payload.writeInt((Integer) arguments.get("frameMax"));
        payload.writeShort((short) arguments.get("heartbeat"));

        respByteBuf.writeInt(payload.readableBytes()).writeBytes(payload).writeByte(0xce);
    }

}
