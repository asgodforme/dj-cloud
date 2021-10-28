package com.dj.cloud.portal.client;

import com.dj.cloud.portal.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 1. feign.Logger.Level: 修改日志级别， NONE, BASIC, HEADERS, FULL
 * 配置文件：
 * 全局生效：feign.client.config.default.loggerLevel=FULL
 * 局部生效： feign.client.config.服务名.loggerLevel=FULL
 * 代码方式：
 * @See FeignClientConfiguration
 * 2. feign.codec.Decoder: 响应结果的解析器， 例如解析json字符串为java对象
 * 3. feign.codec.Encoder: 请求参数编码， 将请求参数编码， 便于通过http请求发送
 * 4. feign.Contract： 支持的注解格式， 默认是springmvc的注解
 * 5. feign.Retryer： 失败重试机制，默认是没有，不过会使用Ribbon的重试
 *
 *
 */
@FeignClient(value="djuser", configuration= FeignClientConfiguration.class)
public interface TimeClient {

    @RequestMapping("/hello")
    String hello();
}
