package com.dj.cloud.facility.netty.websocket.server;

import com.dj.cloud.facility.netty.websocket.domain.ClientMsgProtocol;
import com.dj.cloud.facility.netty.websocket.util.ChannelHandler;
import com.dj.cloud.facility.netty.websocket.util.MsgUtil;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServerHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private WebSocketServerHandshaker handshaker;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        logger.info("链接报告开始");
        logger.info("链接报告信息：有一客户端链接到本服务端");
        logger.info("链接报告IP:{}", channel.localAddress().getHostString());
        logger.info("链接报告Port:{}", channel.localAddress().getPort());
        logger.info("链接报告完毕");
        ChannelHandler.channelGroup.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端断开链接{}", ctx.channel().localAddress().toString());
        ChannelHandler.channelGroup.remove(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) { // http
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            if (!fullHttpRequest.decoderResult().isSuccess()) {
                DefaultFullHttpResponse defaultHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
                // 返回应答给客户端
                if (defaultHttpResponse.status().code() != 200) {
                    ByteBuf buf = Unpooled.copiedBuffer(defaultHttpResponse.status().toString(), CharsetUtil.UTF_8);
                    defaultHttpResponse.content().writeBytes(buf);
                    buf.release();
                }
                // 如果是非Keep-Alive，关闭连接
                ChannelFuture f = ctx.channel().writeAndFlush(defaultHttpResponse);
                if (defaultHttpResponse.status().code() != 200) {
                    f.addListener(ChannelFutureListener.CLOSE);
                }
                return;
            }

            WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws:/" + ctx.channel() + "/websocket", null, false);
            handshaker = wsFactory.newHandshaker(fullHttpRequest);
            if (handshaker == null) {
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            } else {
                handshaker.handshake(ctx.channel(), fullHttpRequest);
            }

            return;
        }

        if (msg instanceof WebSocketFrame) {
            WebSocketFrame webSocketFrame = (WebSocketFrame) msg;

            if (webSocketFrame instanceof CloseWebSocketFrame) {
                handshaker.close(ctx.channel(), ((CloseWebSocketFrame) webSocketFrame).retain());
            }

            if (webSocketFrame instanceof PingWebSocketFrame) {
                ctx.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
                return;
            }

            if (!(webSocketFrame instanceof TextWebSocketFrame)) {
                throw new Exception("仅支持文本格式");
            }

            String request = ((TextWebSocketFrame) webSocketFrame).text();
            System.out.println("微服务收到：" + request);

            ClientMsgProtocol clientMsgProtocol = new Gson().fromJson(request, ClientMsgProtocol.class);

            if (clientMsgProtocol.getType() == 1) {
                ctx.channel().writeAndFlush(MsgUtil.buildMsgOwner(ctx.channel().id().toString()));
                return;
            }

            if (clientMsgProtocol.getType() == 2) {
                TextWebSocketFrame textWebSocketFrame = MsgUtil.buildMsgAll(ctx.channel().id().toString(), clientMsgProtocol.getMsgInfo());
                ChannelHandler.channelGroup.writeAndFlush(textWebSocketFrame);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        logger.info("异常信息：\r\n" + cause.getMessage());
    }
}
