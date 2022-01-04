package mq.quickstart.nettyctmq.codec.mashaller;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import mq.quickstart.nettyctmq.domain.MethodFrame;
import mq.quickstart.nettyctmq.domain.RabbitMQFrame;

import java.util.Map;

public class ConnectionOpenMashaller extends AbstractMashaller {

    @Override
    protected void mashallerArguments(ByteBuf in, Map<String, Object> arguments) {

    }

    @Override
    protected void marshallerOkArgument(RabbitMQFrame rabbitMQFrame, ByteBuf respByteBuf) {
        ByteBuf payload = ByteBufAllocator.DEFAULT.buffer();
        payload.writeShort(10).writeShort(40);
        payload.writeByte(0x01);
        payload.writeBytes("/".getBytes());
        payload.writeShort(0);

        respByteBuf.writeInt(payload.readableBytes()).writeBytes(payload).writeByte(0xce);
    }
}
