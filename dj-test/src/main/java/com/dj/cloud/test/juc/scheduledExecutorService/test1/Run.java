package com.dj.cloud.test.juc.scheduledExecutorService.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Run {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Callable<String>> callableList = new ArrayList<>();
        callableList.add(new MyCallableA());
        callableList.add(new MyCallableB());

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<String> futureA = executor.schedule(callableList.get(0), 4L, TimeUnit.SECONDS);
        ScheduledFuture<String> futureB = executor.schedule(callableList.get(1), 4L, TimeUnit.SECONDS);


        System.out.println("X" + System.currentTimeMillis());
        System.out.println("返回值A： " + futureA.get());
        System.out.println("返回值B： " + futureB.get());
        System.out.println("Y" + System.currentTimeMillis());

    }
}
