package com.dj.cloud.test.juc.scheduledExecutorService.test1;

import java.util.concurrent.Callable;

public class MyCallableA implements Callable<String> {

    @Override
    public String call() throws Exception {
        try {
            System.out.println("callA begin" + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println("callA end" + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "return A";
    }
}
