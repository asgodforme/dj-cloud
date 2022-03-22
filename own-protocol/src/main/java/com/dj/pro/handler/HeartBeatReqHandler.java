package com.dj.pro.handler;

import com.dj.pro.msg.Header;
import com.dj.pro.msg.MessageType;
import com.dj.pro.msg.NettyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {

    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        NettyMessage nettyMessage = (NettyMessage) msg;
        System.out.println("心跳处理器收到请求：" + msg);
        if (nettyMessage.getHeader() != null
                && nettyMessage.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000, TimeUnit.MILLISECONDS);
        } else if (nettyMessage.getHeader() != null
            && nettyMessage.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()) {
            System.out.println("client receive server heart beat message : ---> " + nettyMessage);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private class HeartBeatTask implements Runnable {

        private final ChannelHandlerContext ctx;

        public HeartBeatTask(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            NettyMessage nettyMessage = buildHeartBeat();
            System.out.println("Client send heart beat message to server ---> " + nettyMessage);
            ctx.writeAndFlush(nettyMessage);
        }
    }

    private NettyMessage buildHeartBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_REQ.value());
        message.setHeader(header);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (heartBeat != null) {
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }
}
