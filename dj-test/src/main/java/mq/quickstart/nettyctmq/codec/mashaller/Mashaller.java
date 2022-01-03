package mq.quickstart.nettyctmq.codec.mashaller;

import io.netty.buffer.ByteBuf;
import mq.quickstart.nettyctmq.domain.RabbitMQFrame;

public interface Mashaller {

    /**
     * 字节流转换成rabbit帧对象
     * @param byteBuf
     * @return
     */
    RabbitMQFrame mashallerDo(ByteBuf byteBuf);

    /**
     * 帧对象转换成字节流
     * @param rabbitMQFrame
     * @return
     */
    ByteBuf mashallerOk(RabbitMQFrame rabbitMQFrame);
}
