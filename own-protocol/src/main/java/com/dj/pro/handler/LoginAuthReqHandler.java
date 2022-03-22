package com.dj.pro.handler;

import com.dj.pro.msg.Header;
import com.dj.pro.msg.MessageType;
import com.dj.pro.msg.NettyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 握手认证，用于在通道激活的时候发起握手请求
 */
public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter {

    private NettyMessage buildLoginReq() {
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.value());
        nettyMessage.setHeader(header);
        return nettyMessage;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        NettyMessage sendMsg = buildLoginReq();
        System.out.println("发送登陆验证请求:" + sendMsg);
        ctx.writeAndFlush(sendMsg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage nettyMessage = (NettyMessage) msg;
        if (nettyMessage.getHeader() != null
            && nettyMessage.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            byte loginResult = (byte) nettyMessage.getBody();
            if (loginResult != 0) {
                ctx.close(); //  握手失败，关闭连接
            } else {
                System.out.println("Login is ok: " + nettyMessage);
                ctx.fireChannelRead(msg);
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }
}
