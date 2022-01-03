package mq.quickstart.nettyctmq.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import mq.quickstart.nettyctmq.domain.MethodFrame;
import mq.quickstart.nettyctmq.domain.RabbitMQFrame;

public class MqHandler extends SimpleChannelInboundHandler<RabbitMQFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RabbitMQFrame msg) throws Exception {
        System.out.println("receive: " + msg);
        if (msg.getPayload() instanceof MethodFrame) {
            MethodFrame methodFrame = (MethodFrame) msg.getPayload();
            if (methodFrame.getClassId() == 10 && methodFrame.getMethodId() == 10) {
                System.out.println("接收到Connection.start消息");
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*protocol-header = literal-AMQP protocol-id protocol-version
        literal-AMQP = %d65.77.81.80 ; "AMQP"
        protocol-id = %d0 ; Must be 0
        protocol-version = %d0.9.1 ; 0-9-1*/
        // 当建立TCP连接后，发送协议头给rabbitmq服务器
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byteBuf.writeBytes("AMQP".getBytes());
        byteBuf.writeInt(0x0901);
//        byteBuf.writeInt(2305);
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println(cause);
        ctx.close();
    }
}
