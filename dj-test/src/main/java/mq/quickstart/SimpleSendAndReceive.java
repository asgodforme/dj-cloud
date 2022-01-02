package mq.quickstart;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.nio.charset.StandardCharsets;

public class SimpleSendAndReceive {

    public static void main(String[] args) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("192.168.13.129");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        connectionFactory.addConnectionListener(connection -> {
            System.out.println(connection);
        });

        connectionFactory.addChannelListener((channel, transactional) -> {
            System.out.println("channel: " + channel + ", transactional: " + transactional);
        });

//        AmqpAdmin admin = new RabbitAdmin(connectionFactory);
//        admin.declareQueue(new Queue("myqueue"));
        AmqpTemplate amqpTemplate = new RabbitTemplate(connectionFactory);
//        amqpTemplate.convertAndSend("myqueue", "foo", correlationData);
        Message message = new Message("111111111111111".getBytes(StandardCharsets.UTF_8));
        amqpTemplate.convertAndSend("djCloudDirectExchange", "dj-portal", message);
//        System.out.println("recevie:" + foo);
    }
}
