package mq.quickstart.nettyctmq.codec.mashaller;

import io.netty.buffer.ByteBuf;
import mq.quickstart.nettyctmq.domain.AbstractPayload;
import mq.quickstart.nettyctmq.domain.MethodFrame;
import mq.quickstart.nettyctmq.domain.RabbitMQFrame;

import java.util.HashMap;
import java.util.Map;

public class ConnectionStartMashaller implements Mashaller {

    @Override
    public RabbitMQFrame mashallerDo(ByteBuf in) {
        in.resetReaderIndex();
        RabbitMQFrame rabbitMQFrame = new RabbitMQFrame();
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

    private AbstractPayload marshallerPayload(ByteBuf in) {
        MethodFrame methodFrame = new MethodFrame();
        short classId = in.readShort();
        methodFrame.setClassId(classId);
        short methodId = in.readShort();
        methodFrame.setMethodId(methodId);
        Map<String, Object> arguments = new HashMap<>();
        methodFrame.setArguments(arguments);


        byte versionMajor = in.readByte();
        arguments.put("versionMajor", versionMajor);
        byte versionMinor = in.readByte();
        arguments.put("versionMinor", versionMinor);

        marshallerServerProperties(arguments, in);

        byte[] mechanismsByte = getBytesFromByteBuf(in, in.readInt());
        arguments.put("mechanisms", new String(mechanismsByte));

        byte[] localesByte = getBytesFromByteBuf(in, in.readInt());
        arguments.put("locales", new String(localesByte));

        return methodFrame;
    }

    private void marshallerServerProperties(Map<String, Object> argumentsMap, ByteBuf in) {
        /**
         * Server-properties数据域：
         * Version-Major
         * Version-Minor
         * Server-Properties：第一个字节代表key的长度length, length+1后的四个字节表示类型， length+1+4后的第一个字节表示value长度
         * Mechanisms
         * Locales
         */
        int serverPropertiesLength = in.readInt();
        ByteBuf arguments = in.readBytes(serverPropertiesLength);

        /** 第一个字节代表key的长度length, length+1后的四个字节表示类型， length+1+4后的第一个字节表示value长度
        首先我们要都知道, &表示按位与,只有两个位同时为1,才能得到1, 0x代表16进制数,0xff表示的数二进制1111 1111 占一个字节. 和其进行&操作的数,最低8位,不会发生变化.*/
        while (arguments.readableBytes() != 0) {
            byte[] keyByte = getBytesFromByteBuf(arguments, arguments.readByte());
            String key = new String(keyByte);

            String valueType = Integer.toHexString(arguments.readInt());

            arguments.markReaderIndex();
            byte[] valueByte = getBytesFromByteBuf(arguments, arguments.readByte());
            String value = new String(valueByte);

            /** field table 类型，其中的数据域第一个字节表示key的长度，boolean类型默认为一个字节的t表示，然后一个结束符 */
            if ("46000000".equals(valueType)) {
                Map<String, Object> map = new HashMap<>();
                arguments.resetReaderIndex();
                ByteBuf valueByteBuf = arguments.readBytes(arguments.readByte() & 0xff);
                while (valueByteBuf.readableBytes() != 0) {
                    byte[] subValueByte = getBytesFromByteBuf(valueByteBuf, valueByteBuf.readByte());
                    map.put(new String(subValueByte), (char) valueByteBuf.readByte());
                    valueByteBuf.readByte();
                }
                argumentsMap.put(key, map);
            } else {
                argumentsMap.put(key, value);
            }
        }
    }

    private byte[] getBytesFromByteBuf(ByteBuf arguments, int b) {
        ByteBuf keyByteBuf = arguments.readBytes(b & 0xff);
        byte[] keyByte = new byte[keyByteBuf.readableBytes()];
        keyByteBuf.readBytes(keyByte);
        return keyByte;
    }


    @Override
    public ByteBuf mashallerOk(RabbitMQFrame rabbitMQFrame) {
        return null;
    }
}
