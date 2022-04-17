package com.dj.cloud.gateway.test;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.env.ConfigurableEnvironment;

public class MySpringApplicationRunListener implements SpringApplicationRunListener {

    private final SpringApplication application;

    private final String[] args;

    private final SimpleApplicationEventMulticaster initialMulticaster;

    public MySpringApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
        this.initialMulticaster = new SimpleApplicationEventMulticaster();
        for (ApplicationListener<?> listener : application.getListeners()) {
            this.initialMulticaster.addApplicationListener(listener);
        }
    }

    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println("调用MySpringApplicationRunListener.starting(ConfigurableBootstrapContext bootstrapContext)");
    }


    @Override
    public void starting() {
        System.out.println("调用MySpringApplicationRunListener.starting()");
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        System.out.println("调用MySpringApplicationRunListener.environmentPrepared()");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("调用MySpringApplicationRunListener.environmentPrepared(``)");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("调用MySpringApplicationRunListener.contextPrepared(``)");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("调用MySpringApplicationRunListener.contextLoaded(``)");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("调用MySpringApplicationRunListener.started(``)");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("调用MySpringApplicationRunListener.running(``)");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("调用MySpringApplicationRunListener.failed(``)");
    }
}
