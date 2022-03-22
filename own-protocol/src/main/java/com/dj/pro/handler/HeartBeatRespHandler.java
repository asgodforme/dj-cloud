package com.dj.pro.handler;

import com.dj.pro.msg.Header;
import com.dj.pro.msg.MessageType;
import com.dj.pro.msg.NettyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage nettyMessage = (NettyMessage) msg;
        if (nettyMessage.getHeader() != null
            && nettyMessage.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
            System.out.println("Receive client heart beat message: ---> " + nettyMessage);
            NettyMessage heartBeat = buildHeartBeat();
            System.out.println("send heart beat resposne message to client: ---> " + heartBeat);
            ctx.writeAndFlush(heartBeat);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildHeartBeat() {
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        nettyMessage.setHeader(header);
        return nettyMessage;
    }
}
