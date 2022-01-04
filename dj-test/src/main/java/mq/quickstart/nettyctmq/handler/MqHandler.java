package mq.quickstart.nettyctmq.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import mq.quickstart.nettyctmq.codec.mashaller.*;
import mq.quickstart.nettyctmq.domain.MethodFrame;
import mq.quickstart.nettyctmq.domain.RabbitMQFrame;

public class MqHandler extends SimpleChannelInboundHandler<RabbitMQFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RabbitMQFrame msg) throws Exception {
        System.out.println("receive: " + msg);
        if (msg.getPayload() instanceof MethodFrame) {
            MethodFrame methodFrame = (MethodFrame) msg.getPayload();
            Mashaller mashaller;
            if (methodFrame.getClassId() == 10 && methodFrame.getMethodId() == 10) {
                System.out.println("接收到Connection.start消息");
                mashaller = new ConnectionStartMashaller();
                ByteBuf mashallerOk = mashaller.mashallerOk(msg);
                System.out.println("发送Connection.startOk消息给服务器");
                System.out.println("data: " + mashallerOk);
                ctx.writeAndFlush(mashallerOk);
            } else if (methodFrame.getClassId() == 10 && methodFrame.getMethodId() == 30) {
                System.out.println("接收到Connection.tune消息");
                mashaller = new ConnectionTuneMashaller();
                ByteBuf mashallerOk = mashaller.mashallerOk(msg);
                System.out.println("发送Connection.tuneOk消息给服务器");
                System.out.println("data: " + mashallerOk);
                ctx.writeAndFlush(mashallerOk);

                Mashaller openMashaller = new ConnectionOpenMashaller();
                ctx.writeAndFlush(openMashaller.mashallerOk(msg));
            } else if (methodFrame.getClassId() == 10 && methodFrame.getMethodId() == 41) {
                System.out.println("接收到Connection.openOk消息");
                mashaller = new ChannelOpenMashaller();
                System.out.println("发送Channel.open消息给服务器");
                ctx.writeAndFlush(mashaller.requestPackage(msg));
            } else if (methodFrame.getClassId() == 20 && methodFrame.getMethodId() == 11) {
                System.out.println("接收到Channel.openOk消息");
                System.out.println("准备发送消息");
                Mashaller basicPulibshMashaller = new BasicPublishMashaller();
                ctx.writeAndFlush(basicPulibshMashaller.requestPackage(msg));

            }
        } else {
            System.out.println(msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*
        * protocol-header = literal-AMQP protocol-id protocol-version
        * literal-AMQP = %d65.77.81.80 ; "AMQP"
        * protocol-id = %d0 ; Must be 0
        * protocol-version = %d0.9.1 ; 0-9-1
        * */
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
