package com.dj.cloud.test.netty.protobuf.code;

import com.dj.cloud.test.netty.protobuf.SubscribeReqProto;
import com.dj.cloud.test.netty.protobuf.SubscribeRespProto;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.ArrayList;
import java.util.List;

public class SubReqClientHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client receive: " + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 1; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private SubscribeReqProto.SubscribeReq subReq(int i) {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqId(i);
        builder.setUserName("jiangjie");
        builder.setProductName("netty book");
        List<String> address = new ArrayList<>();
        address.add("hunan yueyang1");
        address.add("hunan yueyang1");
        address.add("hunan yueyang1");
        address.add("hunan yueyang1");
        address.add("hunan yueyang1");
        builder.addAllAddress(address);
        return builder.build();
    }
}
