package com.dj.cloud.rabbitmq.listener;

import com.dj.cloud.rabbitmq.config.RabbitMqConfiguration;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.application.name}" + RabbitMqConfiguration.QUEUE_NAME_SUFFIX, durable = "true"),
            exchange = @Exchange(value = RabbitMqConfiguration.EXCHANGE_DIRECT_NAME, ignoreDeclarationExceptions = "true"),
            key = "${spring.application.name}"
    ))
    public void receiveMessage(String data) {
        System.out.println("receive: " + data);
    }
}
