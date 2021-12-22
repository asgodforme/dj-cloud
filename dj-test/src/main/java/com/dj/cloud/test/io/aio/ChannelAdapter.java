package com.dj.cloud.test.io.aio;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public abstract class ChannelAdapter implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel channel;
    private Charset charset;

    public ChannelAdapter(AsynchronousSocketChannel channel, Charset charset) {
        this.channel = channel;
        this.charset = charset;
        if (channel.isOpen()) {
            channelActive(new ChannelHandler(channel, charset));
        }
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        System.out.println("接收到消息result: " + result + ", attachment: " + attachment + ", channel: " + channel);
        try {
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            final long timeout = 60 * 60L;
            channelRead(new ChannelHandler(channel, charset), "charset.decode(buffer)");
            channel.read(buffer, timeout, TimeUnit.SECONDS, null, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println("dddddd");
                    if (result == -1) {
                        try {
                            channelInactive(new ChannelHandler(channel, charset));
                            channel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    buffer.flip();
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    System.out.println("收到消息：" + new String(bytes, charset));
                    channelRead(new ChannelHandler(channel, charset), charset.decode(buffer));
                    buffer.clear();
                    channel.read(buffer, timeout, TimeUnit.SECONDS, null, this);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    System.out.println("dddddd111");
                    exc.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        exc.printStackTrace();
    }

    /**
     * 通道激活时触发的事件
     * @param ctx
     */
    public abstract void channelActive(ChannelHandler ctx);

    /**
     * 通道断开时触发的事件
     * @param ctx
     */
    public abstract void channelInactive(ChannelHandler ctx);

    /**
     * 通道读取数据
     * @param ctx
     * @param msg
     */
    public abstract void channelRead(ChannelHandler ctx, Object msg);
}
