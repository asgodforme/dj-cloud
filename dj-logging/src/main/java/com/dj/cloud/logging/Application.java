package com.dj.cloud.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Application {

    private static final Logger logger = LogManager.getLogger("HelloWorld");

    public static void main(String[] args) {
        logger.info("Hello, world!");
        logger.debug("logging in user {} with birthday {}", "jiangjie", "0126");
        SpringApplication.run(Application.class, args);
    }
}
