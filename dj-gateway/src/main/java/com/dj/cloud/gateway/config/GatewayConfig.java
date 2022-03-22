package com.dj.cloud.gateway.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class GatewayConfig implements InitializingBean {
    @Autowired
    private DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator;
    @Override
    public void afterPropertiesSet() throws Exception {
        discoveryClientRouteDefinitionLocator.getRouteDefinitions().subscribe(System.out::println);
    }
}
