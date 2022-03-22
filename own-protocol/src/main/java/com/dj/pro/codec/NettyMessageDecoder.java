package com.dj.pro.codec;

import com.dj.pro.msg.Header;
import com.dj.pro.msg.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

    MarshallingDecoder marshallingDecoder;

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) throws IOException {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        marshallingDecoder = new MarshallingDecoder();
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        System.out.println("进入解码器NettyMessageDecoder: " + in);
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        System.out.println("super.decode: " + frame);
//        if (frame == null) {
//            return null;
//        }
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(in.readInt());
        header.setLength(in.readInt());
        header.setSessionId(in.readLong());
        header.setType(in.readByte());
        header.setPriority(in.readByte());

        int size = in.readInt();
        if (size > 0) {
            Map<String, Object> attch = new HashMap<>(size);
            for (int i = 0; i < size; i++) {
                int keySize = in.readInt();
                byte[] keyArray = new byte[keySize];
                in.readBytes(keyArray);
                String key = new String(keyArray, StandardCharsets.UTF_8);
                attch.put(key, marshallingDecoder.decode(in));
            }
            header.setAttachement(attch);
        }

        if (in.readableBytes() > 4) {
            nettyMessage.setBody(marshallingDecoder.decode(in));
        } else {
            in.skipBytes(in.readableBytes());
        }

        nettyMessage.setHeader(header);
        System.out.println("解码后的信息：" + nettyMessage + ", in : " + in);
        return nettyMessage;
    }
}
