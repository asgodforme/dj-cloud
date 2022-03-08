package com.dj.cloud.test;

import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;

import java.util.Map;

public class ThreadLocalTEST {

     static final ThreadLocal<Map<String, String>> context = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            Map<String, String> map = Maps.newHashMap();
            map.put("t", "t thread");
            context.set(map);
        });
        t.start();
        t.join();
        Map<String, String> contextMap = context.get();
        if (CollectionUtils.isEmpty(contextMap)) {
            contextMap = Maps.newHashMap();
        }
        contextMap.put("main", "main thread");
        context.set(contextMap);

        System.out.println(context.get());


    }
}
