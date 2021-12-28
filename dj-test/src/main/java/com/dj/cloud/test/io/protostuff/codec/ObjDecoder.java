package com.dj.cloud.test.io.protostuff.codec;

import com.dj.cloud.test.io.protostuff.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.springframework.util.SerializationUtils;

import java.util.List;

public class ObjDecoder extends ByteToMessageDecoder {

    private Class<?> genericClass;

    public ObjDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        // 标记当前读取位置
        in.markReaderIndex();
        // 读取数据长度
        int dataLength = in.readInt();
        // 剩余可读的字节少于数据长度，表示数据还没有全部发送过来，需要等待下一次发送过来
        if (in.readableBytes() < dataLength) {
            // 重置读取标记
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);
        out.add(SerializationUtil.deserialize(data, genericClass));
    }
}
