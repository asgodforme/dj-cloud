package mq.quickstart.nettyctmq.codec.mashaller;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import mq.quickstart.nettyctmq.domain.MethodFrame;
import mq.quickstart.nettyctmq.domain.RabbitMQFrame;

import java.util.HashMap;
import java.util.Map;

public class ConnectionStartMashaller extends AbstractMashaller {

    private static final Map<String, String> clientPropMap = new HashMap<>();

    static {
        clientPropMap.put("connection_name", "netty-connection-rabbitmq");
        clientPropMap.put("product", "RabbitMQ");
        clientPropMap.put("copyright", "jiang@jie.com");
        clientPropMap.put("information", "hello, rabbitmq");
        clientPropMap.put("version", "1.1.1");
        clientPropMap.put("platform", "java");
    }

    @Override
    protected void mashallerArguments(ByteBuf in, Map<String, Object> arguments) {
        byte versionMajor = in.readByte();
        arguments.put("versionMajor", versionMajor);
        byte versionMinor = in.readByte();
        arguments.put("versionMinor", versionMinor);

        marshallerServerProperties(arguments, in);

        byte[] mechanismsByte = getBytesFromByteBuf(in, in.readInt());
        arguments.put("mechanisms", new String(mechanismsByte));

        byte[] localesByte = getBytesFromByteBuf(in, in.readInt());
        arguments.put("locales", new String(localesByte));
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

            /** field table 类型，其中的数据域第一个字节表示key的长度，boolean类型默认为一个字节的t表示，然后一个结束符 */
            if ("46000000".equals(valueType)) {
                Map<String, Object> map = new HashMap<>();
                ByteBuf valueByteBuf = arguments.readBytes(arguments.readByte() & 0xff);
                byte[] capabilities = new byte[valueByteBuf.readableBytes()];
                valueByteBuf.readBytes(capabilities);
                argumentsMap.put(key + "_bytes", capabilities);
                valueByteBuf.resetReaderIndex();
                while (valueByteBuf.readableBytes() != 0) {
                    byte[] subValueByte = getBytesFromByteBuf(valueByteBuf, valueByteBuf.readByte());
                    map.put(new String(subValueByte), (char) valueByteBuf.readByte());
                    valueByteBuf.readByte();
                }
                argumentsMap.put(key, map);
            } else {
                byte[] valueByte = getBytesFromByteBuf(arguments, arguments.readByte());
                String value = new String(valueByte);
                argumentsMap.put(key, value);
            }
        }
    }

    @Override
    protected void marshallerOkArgument(RabbitMQFrame rabbitMQFrame, ByteBuf respByteBuf) {
        // Arguments: Client-Properties, Mechanism, Response, Locale
        ByteBuf arguments = ByteBufAllocator.DEFAULT.buffer();
        // Client-Properties: 1字节来描述key的长度length+ length长度的key + 4字节的类型 + 1字节的长度length1 + length1长度的value
        MethodFrame methodFrame = (MethodFrame) rabbitMQFrame.getPayload();
        ByteBuf clientProperties = ByteBufAllocator.DEFAULT.buffer();
        populateClientProperties(clientProperties, "capabilities", (byte[]) methodFrame.getArguments().get("capabilities_bytes"));
        clientPropMap.forEach((key, value) -> {
            populateClientProperties(clientProperties, key, value);
        });

        arguments.writeInt(clientProperties.readableBytes());
        arguments.writeBytes(clientProperties);

        // Mechanism
        byte[] mechanism = "PLAIN".getBytes();
        arguments.writeByte(mechanism.length);
        arguments.writeBytes(mechanism);

        // Response
        ByteBuf rspByteBuf = ByteBufAllocator.DEFAULT.buffer();
        rspByteBuf.writeByte(0);
        rspByteBuf.writeBytes("guest".getBytes());
        rspByteBuf.writeByte(0);
        rspByteBuf.writeBytes("guest".getBytes());
        arguments.writeInt(rspByteBuf.readableBytes());
        arguments.writeBytes(rspByteBuf);

        // Locale
        byte[] locale = "en_US".getBytes();
        arguments.writeByte(locale.length);
        arguments.writeBytes(locale);

        // payload: Class, Method, Arguments
        ByteBuf payload = ByteBufAllocator.DEFAULT.buffer();
        payload.writeShort(10).writeShort(11).writeBytes(arguments);

        // 数据长度
        respByteBuf.writeInt(payload.readableBytes()).writeBytes(payload).writeByte(0xce);
    }
}
