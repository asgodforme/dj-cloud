package com.dj.cloud.test.netty.protobuf.code;

import com.dj.cloud.test.netty.protobuf.SubscribeReqProto;
import com.dj.cloud.test.netty.protobuf.SubscribeRespProto;
import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

public class TestSubscribeReqProto {

    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqId(1);
        builder.setUserName("jiangjie");
        builder.setProductName("netty book");
        List<String> address = new ArrayList<>();
        address.add("hunan yueyang1");
        address.add("hunan yueyang2");
        address.add("hunan yueyang3");
        address.add("hunan yueyang4");
        builder.addAllAddress(address);
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println("Before encode: " + req.toString());
        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
        System.out.println("After decode: " + req2.toString());
        System.out.println("Assert equal: " + req2.equals(req));
    }
}
