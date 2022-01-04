package mq.quickstart.nettyctmq.codec.mashaller;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import mq.quickstart.nettyctmq.domain.RabbitMQFrame;

import java.util.Map;

public class BasicPublishMashaller extends AbstractMashaller {
    @Override
    protected void mashallerArguments(ByteBuf in, Map<String, Object> arguments) {

    }

    @Override
    protected void marshallerOkArgument(RabbitMQFrame rabbitMQFrame, ByteBuf respByteBuf) {

    }

    @Override
    public ByteBuf requestPackage(RabbitMQFrame rabbitMQFrame) {

        String content = "hello,world!";

        // 使用方法帧，内容头帧和消息体帧向rabbitmq发送消息
        ByteBuf byteBuf = mashallerOk(rabbitMQFrame);

        // 方法帧数据负荷
        ByteBuf methodFramePayload = ByteBufAllocator.DEFAULT.buffer();
        methodFramePayload.writeShort(60); // Basic
        methodFramePayload.writeShort(40); // Publish
        methodFramePayload.writeByte(0); // Ticket
        methodFramePayload.writeByte(0);

        // Exchange
        ByteBuf exchangeBuf = ByteBufAllocator.DEFAULT.buffer();
        byte[] exchangeName = "djCloudDirectExchange".getBytes();
        exchangeBuf.writeByte(exchangeName.length);
        exchangeBuf.writeBytes(exchangeName);
        methodFramePayload.writeBytes(exchangeBuf);

        // Routing-key
        ByteBuf routingKeyBuf = ByteBufAllocator.DEFAULT.buffer();
        byte[] routingKeyName = "dj-portal".getBytes();
        routingKeyBuf.writeByte(routingKeyName.length);
        routingKeyBuf.writeBytes(routingKeyName);
        methodFramePayload.writeBytes(routingKeyBuf);
        methodFramePayload.writeByte(0);

        byteBuf.writeInt(methodFramePayload.readableBytes()).writeBytes(methodFramePayload);
        byteBuf.writeByte(0xce);
        // 内容头帧
        byteBuf.writeByte(2);
        byteBuf.writeShort(rabbitMQFrame.getChannelNo());

        ByteBuf contentBuf = ByteBufAllocator.DEFAULT.buffer();
        contentBuf.writeShort(60);
        contentBuf.writeShort(0);
        contentBuf.writeLong(content.getBytes().length);
        contentBuf.writeShort(0xb800);

        ByteBuf properteisbuf = ByteBufAllocator.DEFAULT.buffer();
        properteisbuf.writeBytes("application/octet-stream".getBytes());
        contentBuf.writeByte(properteisbuf.readableBytes()).writeBytes(properteisbuf);

        contentBuf.writeInt(0);
        contentBuf.writeByte(2);
        contentBuf.writeByte(0);


        byteBuf.writeInt(contentBuf.readableBytes()).writeBytes(contentBuf);

        byteBuf.writeByte(0xce);

        // 消息体帧
        byteBuf.writeByte(3);
        byteBuf.writeShort(rabbitMQFrame.getChannelNo());
        byteBuf.writeInt(content.getBytes().length);
        byteBuf.writeBytes(content.getBytes());
        byteBuf.writeByte(0xce);
        return byteBuf;
    }
}
