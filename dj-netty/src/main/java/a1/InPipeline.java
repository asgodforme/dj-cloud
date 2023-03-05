package a1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InPipeline {
    static class SimpleInhandlerA extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("入站处理器A ：被回调");
//            super.channelRead(ctx, msg);
        }
    }

    static class SimpleInhandlerB extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("入站处理器B ：被回调");
//            super.channelRead(ctx, msg);
        }
    }

    static class SimpleInhandlerC extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            log.info("入站处理器C ：被回调");
//            super.channelRead(ctx, msg);
        }
    }

    public static void main(String[] args) {
        ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel embeddedChannel) throws Exception {
                embeddedChannel.pipeline().addLast(new SimpleInhandlerA());
                embeddedChannel.pipeline().addLast(new SimpleInhandlerB());
                embeddedChannel.pipeline().addLast(new SimpleInhandlerC());
            }
        };

        EmbeddedChannel embeddedChannel = new EmbeddedChannel(i);
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1);
        embeddedChannel.writeInbound(buf);
    }
}
