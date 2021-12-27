package com.dj.cloud.test.io.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class MyDecoder extends ByteToMessageDecoder {
    private final int BASE_LENGTH = 4;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 02 34 68 69 68 69 03
        System.out.println("mydecoder:" + in);
        //基础长度不足，我们设定基础长度为4
        System.out.println("in.readableBytes():" + in.readableBytes());
        if (in.readableBytes() < BASE_LENGTH) {
            return;
        }

        int beginIdx; //记录包头位置

        while (true) {
            // 获取包头开始的index
            beginIdx = in.readerIndex();
            // 标记包头开始的index
            in.markReaderIndex();
            // 读到了协议的开始标志，结束while循环
            if (in.readByte() == 0x02) {
                break;
            }
            // 未读到包头，略过一个字节
            // 每次略过，一个字节，去读取，包头信息的开始标记
//            in.resetReaderIndex();
//            in.readByte();
            // 当略过，一个字节之后，
            // 数据包的长度，又变得不满足
            // 此时，应该结束。等待后面的数据到达
            if (in.readableBytes() < BASE_LENGTH) {
                return;
            }

        }
        System.out.println("确定包头位置为：" + beginIdx + ", ..." + in.getByte(beginIdx));
        //判断存在有数据长度的时候才往后继续读取
        int readableCount = in.readableBytes();
        if (readableCount <= 1) {
            in.readerIndex(beginIdx);
            return;
        }

        //处理包头标志后的数据长度十六进制的0x34 十进制的52 字符串4
        ByteBuf byteBuf = in.readBytes(1);
        String msgLengthStr = byteBuf.toString(StandardCharsets.UTF_8);
        System.out.println("读取到数据域长度："+ msgLengthStr);
        int msgLength = Integer.parseInt(msgLengthStr);

        //剩余长度不足可读取数量[没有结尾标识]
        readableCount = in.readableBytes();
        if (readableCount < msgLength + 1) {
            in.readerIndex(beginIdx);
            return;
        }

        ByteBuf msgContent = in.readBytes(msgLength);

        //如果没有结尾标识，还原指针位置[其他标识结尾]
        byte end = in.readByte();
        if (end != 0x03) {
            in.readerIndex(beginIdx);
            return;
        }
        System.out.println("msgContext: " + msgContent);
        out.add(msgContent.toString(StandardCharsets.UTF_8));
    }
}
