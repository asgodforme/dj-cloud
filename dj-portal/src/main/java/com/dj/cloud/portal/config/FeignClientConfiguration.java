package com.dj.cloud.portal.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;


public class FeignClientConfiguration {

    /**
     * 生命feignClient的日志级别
     * 全局： @EnableFeignClients(defaultConfiguration= FeignClientConfiguration.class)
     * 局部： @FiegnClient(value="服务名" configuration=FeignClientConfiguration.class)
     */
    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.BASIC;
    }
}
