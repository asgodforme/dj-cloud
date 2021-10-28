package com.dj.cloud.portal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * nacos配置自动刷新有两种方式：
 * 1. 通过@Value注入，通过@RefreshScope配置自动刷新
 * 2. 通过@ConfigurationProperties直接实现
 *
 * 多环境配置共享优先级：（服务名-profile.yml > 服务名称.yml配置都会被记载）
 * 服务名-profile.yml > 服务名称.yml > 本地配置
 */
@Component
@ConfigurationProperties(prefix = "pattern")
public class PatternProperties {
    private String dateformat;

    public String getDateformat() {
        return dateformat;
    }

    public void setDateformat(String dateformat) {
        this.dateformat = dateformat;
    }
}
