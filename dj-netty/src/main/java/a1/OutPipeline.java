package a1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OutPipeline {

    static class SimpleOutHandlerA extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            log.info("out bound A called.");
//            super.write(ctx, msg, promise);
        }
    }

    static class SimpleOutHandlerB extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            log.info("out bound B called.");
//            super.write(ctx, msg, promise);
        }
    }

    static class SimpleOutHandlerC extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            log.info("out bound C called.");
//            super.write(ctx, msg, promise);
        }
    }

    public static void main(String[] args) {
        ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel embeddedChannel) throws Exception {
                embeddedChannel.pipeline().addLast(new SimpleOutHandlerA());
                embeddedChannel.pipeline().addLast(new SimpleOutHandlerB());
                embeddedChannel.pipeline().addLast(new SimpleOutHandlerC());
            }
        };
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(i);
        ByteBuf b = Unpooled.buffer();
        b.writeInt(1);
        embeddedChannel.writeOutbound(b);
    }
}
