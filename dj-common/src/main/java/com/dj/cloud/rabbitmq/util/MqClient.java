package com.dj.cloud.rabbitmq.util;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class MqClient {

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendAndReceive(String appName) {
        Message message = new Message((appName + "您好！我是" + this.appName).getBytes(StandardCharsets.UTF_8));
        System.out.println(rabbitTemplate.sendAndReceive(appName, message));
    }
}
