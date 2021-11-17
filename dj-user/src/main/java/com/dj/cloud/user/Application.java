package com.dj.cloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 服务提供者：
 *      临时实例： 采用心跳监测
 *      非临时实例： nacos主动询问
 * 服务消费者：
 *       1. 定时拉取 2.主动推送
 *
 *
 *  Eureka与nacos的共同点：
 *  1. 都支持服务注册和服务拉取
 *  2. 都支持服务提供者心跳方式做健康检测
 *
 *  区别：
 *  1.nacos支持主动检测提供者状态，临时实例采用心跳模式，非临时实例使用主动检测模式
 *  2. 临时实例心跳不正常会被剔除，非临时实例则不会被剔除
 *  3. nacos支持服务列表变更的消息推送，服务列表及时更新
 *  4. nacos集群默认采用AP方式，当集群中存在非临时实例时采用CP模式，Eureka采用AP模式
 *
 *
 */
@SpringBootApplication
@EnableDiscoveryClient // 开启注册到注册中心
@EnableFeignClients // 开启声明式接口调用feign
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
