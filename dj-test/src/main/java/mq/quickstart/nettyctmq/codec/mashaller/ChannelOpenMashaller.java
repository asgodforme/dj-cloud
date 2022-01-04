package mq.quickstart.nettyctmq.codec.mashaller;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import mq.quickstart.nettyctmq.domain.RabbitMQFrame;

import java.util.Map;

public class ChannelOpenMashaller extends AbstractMashaller {
    @Override
    protected void mashallerArguments(ByteBuf in, Map<String, Object> arguments) {

    }

    @Override
    protected void marshallerOkArgument(RabbitMQFrame rabbitMQFrame, ByteBuf respByteBuf) {

    }

    @Override
    public ByteBuf requestPackage(RabbitMQFrame rabbitMQFrame) {
        ByteBuf resp = super.requestPackage(rabbitMQFrame);

        ByteBuf payload = ByteBufAllocator.DEFAULT.buffer();
        payload.writeShort(20);
        payload.writeShort(10);
        payload.writeByte(0);
        resp.writeInt(payload.readableBytes()).writeBytes(payload).writeByte(0xce);

        return resp;
    }
}
