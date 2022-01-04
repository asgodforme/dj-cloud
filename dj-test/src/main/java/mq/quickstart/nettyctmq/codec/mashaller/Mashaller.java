package mq.quickstart.nettyctmq.codec.mashaller;

import io.netty.buffer.ByteBuf;
import mq.quickstart.nettyctmq.domain.RabbitMQFrame;

public interface Mashaller {

    int PROPERTY_STRING = 0x53000000;
    int PROPERTY_FIELD_TABLE = 0x46000000;

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

    ByteBuf requestPackage(RabbitMQFrame rabbitMQFrame);
}
