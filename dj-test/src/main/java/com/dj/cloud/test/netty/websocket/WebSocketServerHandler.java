package com.dj.cloud.test.netty.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.time.LocalTime;
import java.util.logging.Logger;

import static io.netty.handler.codec.http.HttpUtil.isKeepAlive;
import static io.netty.handler.codec.http.HttpUtil.setContentLength;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger logger = Logger.getLogger(WebSocketServerHandler.class.getName());

    private WebSocketServerHandshaker handshaker;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 首次握手消息由http协议承载
        if (msg instanceof FullHttpRequest) {
            System.out.println("握手消息来了");
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }

    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame request) throws UnsupportedEncodingException {
        if (request instanceof CloseWebSocketFrame) {
            System.out.println("接收到关闭链路的请求。");
            handshaker.close(ctx.channel(), ((CloseWebSocketFrame) request).retain());
            return;
        }

        if (request instanceof PingWebSocketFrame) {
            System.out.println("接收到ping请求");
            ctx.channel().write(new PongWebSocketFrame(request.content().retain()));
            return;
        }

        if (!(request instanceof TextWebSocketFrame)) {
            System.out.println("只支持文本消息");
            throw new UnsupportedOperationException(String.format("%s frame types not supported", request.getClass().getName()));
        }
        byte[] bytes = new byte[request.content().readableBytes()];
        request.content().readBytes(bytes);
        ctx.channel().write(new TextWebSocketFrame(new String(bytes, "UTF-8") + ",欢迎使用Netty WebSocket服务，现在时刻：" + LocalTime.now()));

    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        if (!request.decoderResult().isSuccess()
                || !"websocket".equals(request.headers().get("Upgrade"))) {
            sendHttpResponse(ctx, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:8080/websocket", null, false);
        handshaker = wsFactory.newHandshaker(request);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), request);
        }
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response) {
        if (response.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(response.status().toString(), Charset.defaultCharset());
            response.content().writeBytes(buf);
            response.release();
            setContentLength(response, response.content().readableBytes());
        }
        ChannelFuture f = ctx.channel().writeAndFlush(response);
        if (!isKeepAlive(request) || response.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
