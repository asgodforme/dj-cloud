package com.dj.cloud.test.netty.nettyProtocol;


import io.netty.buffer.ByteBuf;

import javax.xml.bind.Marshaller;

public class MarshallingEncoder {
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
    Marshaller marshaller;

//    public MarshallingEncoder() {
//        this.marshaller = MarshallingCodecFactory.buildMarshalling();
//    }
//
//    protected void encode(Object msg, ByteBuf out) {
//        int lengthPos = out.writerIndex();
//        out.writeBytes(LENGTH_PLACEHOLDER);
//        ChannelBufferByteOutput output = new ChannelBufferByteOutput();
//    }

}
