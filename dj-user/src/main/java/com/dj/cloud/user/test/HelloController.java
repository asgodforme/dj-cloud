package com.dj.cloud.user.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/user")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello, I am dj-user";
    }
}
