package com.dj.cloud.facility;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 开启注册到注册中心
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
