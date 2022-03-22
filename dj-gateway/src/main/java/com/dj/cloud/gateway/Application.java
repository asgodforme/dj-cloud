package com.dj.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关的作用
 * 1. 对用户请求做身份认证，权限校验
 * 2. 对用户请求路由到微服务，并实现负载均衡
 * 3. 对用户请求做限流
 * gateway与zuul的区别：
 * Zuul是基于Servlet的实现，属于阻塞式编程。而springgateway是基于spring5中的webflux,属于响应式编程的实现，具备更好的性能。吞吐量更高！
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
