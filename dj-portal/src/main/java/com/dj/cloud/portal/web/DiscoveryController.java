package com.dj.cloud.portal.web;

import com.dj.cloud.portal.config.PatternProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
//@RefreshScope // 配置自动刷新
@RequestMapping("/portal")
public class DiscoveryController {

//    @Value("${pattern.dateformat}")
//    private String parttern;

    @Autowired
    private PatternProperties patternProperties;

//    @Autowired
//    private TimeClient timeClient;

    @GetMapping("now")
    public String now(@RequestHeader(value = "jiangjie", required = false) String jiangjie) {
        System.out.println(jiangjie);
//        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(parttern));
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateformat()));
    }

//    @GetMapping("nowByFeign")
//    public String nowByFeign() {
//        return timeClient.hello();
//    }
}
