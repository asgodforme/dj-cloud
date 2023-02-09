package producer;

import io.netty.channel.DefaultChannelId;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class SyncProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer myProducer = new DefaultMQProducer("MyGroup1");
        myProducer.setNamesrvAddr("120.78.15.216:9876");
        DefaultChannelId.newInstance();
        myProducer.start();
        String body = "Hello RocketMQ";
        for (int i = 0; i < 100; i++) {
            Message message = new Message("TopicTest", "TagA", (body + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult result = myProducer.send(message);
            System.out.println("sendResult: " + result);
        }
        myProducer.shutdown();
    }
}
