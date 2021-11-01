package com.dj.cloud.portal.web;

import com.dj.cloud.portal.config.PatternProperties;
import com.jd.cloud.feign.client.TimeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
//@RefreshScope // 配置自动刷新
public class DiscoveryController {

//    @Value("${pattern.dateformat}")
//    private String parttern;

    @Autowired
    private PatternProperties patternProperties;

    @Autowired
    private TimeClient timeClient;

    @GetMapping("now")
    public String now() {
//        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(parttern));
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateformat()));
    }

    @GetMapping("nowByFeign")
    public String nowByFeign() {
        return timeClient.hello();
    }

}
