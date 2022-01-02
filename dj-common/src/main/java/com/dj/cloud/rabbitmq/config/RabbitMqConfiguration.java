package com.dj.cloud.rabbitmq.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置
 */
@Configuration
public class RabbitMqConfiguration {


    /**
     * 使用当前应用的名称作为消息队列中的routingKey
     */
    @Value("${spring.application.name}")
    private String appName;

    public static final String EXCHANGE_DIRECT_NAME = "djCloudDirectExchange";
    public static final String QUEUE_NAME_SUFFIX = "-queue";



    /**
     * spring-amqp mq连接工厂 用于管理连接资源
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("192.168.13.129");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    /**
     * spring-amqp RabbitAdmin管理配置 用于声明交换机，队列，绑定关系
     * @return
     */
    @Bean
    public AmqpAdmin amqpAdmin() {
        RabbitAdmin amqpAdmin = new RabbitAdmin(connectionFactory());
        amqpAdmin.setAutoStartup(true);
        // 声明交换机
        amqpAdmin.declareExchange(new DirectExchange(EXCHANGE_DIRECT_NAME));
        // 声明队列
        amqpAdmin.declareQueue(new Queue(appName + QUEUE_NAME_SUFFIX));
        // 将交换机通过路由键绑定队列
        amqpAdmin.declareBinding(new Binding(appName + QUEUE_NAME_SUFFIX, Binding.DestinationType.QUEUE, EXCHANGE_DIRECT_NAME, appName, null));
        return amqpAdmin;
    }

    /**
     * spring-amqp 发送接收消息模板类
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setExchange(EXCHANGE_DIRECT_NAME);
        return rabbitTemplate;
    }

//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
//        container.setQueueNames(QUEUE_NAME);
//        container.setMessageListener(new ChannelAwareMessageListener() {
//            @Override
//            public void onMessage(Message message, Channel channel) throws Exception {
//                String msg = new String(message.getBody());
//                System.err.println("----------消费者: " + msg);
//            }
//        });
//        return container;
//    }
}
