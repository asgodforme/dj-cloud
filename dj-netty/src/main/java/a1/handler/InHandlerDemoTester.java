package a1.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

public class InHandlerDemoTester {
    @Test
    public static void testInHandlerLifeCircle() {
        final InHandlerDemo inHandlerDemo = new InHandlerDemo();

        ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {

            @Override
            protected void initChannel(EmbeddedChannel embeddedChannel) throws Exception {
                embeddedChannel.pipeline().addLast(inHandlerDemo);
            }
        };


        EmbeddedChannel embeddedChannel = new EmbeddedChannel(i);
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1);
        embeddedChannel.writeInbound(buf);
        embeddedChannel.flush();
        embeddedChannel.writeInbound(buf);
        embeddedChannel.flush();
        embeddedChannel.close();
    }

    public static void main(String[] args) {
        testInHandlerLifeCircle();
    }
}
