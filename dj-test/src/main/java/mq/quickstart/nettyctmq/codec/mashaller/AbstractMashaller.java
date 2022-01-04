package mq.quickstart.nettyctmq.codec.mashaller;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import mq.quickstart.nettyctmq.domain.AbstractPayload;
import mq.quickstart.nettyctmq.domain.MethodFrame;
import mq.quickstart.nettyctmq.domain.RabbitMQFrame;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractMashaller implements Mashaller {

    @Override
    public RabbitMQFrame mashallerDo(ByteBuf in) {
        RabbitMQFrame rabbitMQFrame = new RabbitMQFrame();
        in.resetReaderIndex();
        byte type = in.readByte(); // 帧类型
        rabbitMQFrame.setFrameType(type);
        short channel = in.readShort(); // 信道编号
        rabbitMQFrame.setChannelNo(channel);
        long size = in.readInt(); // 帧大小
        rabbitMQFrame.setFrameLength(size);

        ByteBuf payload = in.readBytes((int) size);
        rabbitMQFrame.setPayload(marshallerPayload(payload));
        String frameEnd = Integer.toHexString(in.readByte() & 0xff); // 结束标识
        rabbitMQFrame.setEndFlg(frameEnd);
        return rabbitMQFrame;
    }

    /**
     * 解析协议中数据负载不同的部分
     * @param in
     * @return
     */
    protected final AbstractPayload marshallerPayload(ByteBuf in) {
        MethodFrame methodFrame = new MethodFrame();
        short classId = in.readShort();
        methodFrame.setClassId(classId);
        short methodId = in.readShort();
        methodFrame.setMethodId(methodId);
        Map<String, Object> arguments = new HashMap<>();
        methodFrame.setArguments(arguments);

        mashallerArguments(in, arguments);

        return methodFrame;
    }

    protected abstract void mashallerArguments(ByteBuf in, Map<String, Object> arguments);

    /**
     * 从缓冲的中读取指定长度的数据返回字节数组
     * @param arguments
     * @param b
     * @return
     */
    protected byte[] getBytesFromByteBuf(ByteBuf arguments, int b) {
        ByteBuf keyByteBuf = arguments.readBytes(b & 0xff);
        byte[] keyByte = new byte[keyByteBuf.readableBytes()];
        keyByteBuf.readBytes(keyByte);
        return keyByte;
    }

    /**
     * 将指定的key-value塞入缓冲
     * @param clientProperties
     * @param key
     * @param value
     */
    protected void populateClientProperties(ByteBuf clientProperties, String key, String value) {
        byte[] keyBytes = key.getBytes();
        clientProperties.writeByte(keyBytes.length);
        clientProperties.writeBytes(keyBytes);
        clientProperties.writeInt(PROPERTY_STRING);
        byte[] valueBytes = value.getBytes();
        clientProperties.writeByte(valueBytes.length);
        clientProperties.writeBytes(valueBytes);
    }

    /**
     * 将指定的key-byte[]塞入缓冲中
     * @param clientProperties
     * @param key
     * @param valueBytes
     */
    protected void populateClientProperties(ByteBuf clientProperties, String key, byte[] valueBytes) {
        byte[] keyBytes = key.getBytes();
        clientProperties.writeByte(keyBytes.length);
        clientProperties.writeBytes(keyBytes);
        clientProperties.writeInt(PROPERTY_FIELD_TABLE);
        clientProperties.writeByte(valueBytes.length);
        clientProperties.writeBytes(valueBytes);
    }

    @Override
    public ByteBuf mashallerOk(RabbitMQFrame rabbitMQFrame) {
        ByteBuf respByteBuf = ByteBufAllocator.DEFAULT.buffer();
        // 帧类型
        respByteBuf.writeByte(rabbitMQFrame.getFrameType());
        // 信道编号
        respByteBuf.writeShort(rabbitMQFrame.getChannelNo());
        marshallerOkArgument(rabbitMQFrame, respByteBuf);
        return respByteBuf;
    }

    protected abstract void marshallerOkArgument(RabbitMQFrame rabbitMQFrame, ByteBuf respByteBuf);

    @Override
    public ByteBuf requestPackage(RabbitMQFrame rabbitMQFrame) {
        rabbitMQFrame.setChannelNo((short) (rabbitMQFrame.getChannelNo() + 1));
        return mashallerOk(rabbitMQFrame);
    }
}
