package org.itstack.demo.netty2.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.itstack.demo.netty2.future.SyncWriteFuture;
import org.itstack.demo.netty2.future.SyncWriteMap;
import org.itstack.demo.netty2.msg.Response;

/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：bugstack虫洞栈  ｛关注获取学习源码｝
 * 虫洞群：①群5398358 ②群5360692
 * Create by fuzhengwei on 2019
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
        System.out.println("调用客户端的channelRead");
        Response msg = (Response) obj;
        String requestId = msg.getRequestId();
        SyncWriteFuture future = (SyncWriteFuture) SyncWriteMap.syncKey.get(requestId);
        if (future != null) {
            future.setResponse(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
