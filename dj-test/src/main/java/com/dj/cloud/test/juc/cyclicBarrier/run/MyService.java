package com.dj.cloud.test.juc.cyclicBarrier.run;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyService {

    private CyclicBarrier cyclicBarrier;

    public MyService(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public void beginRun() {
        try {
//            long sleepValue = (long) (Math.random() * 10000);
//            Thread.sleep(sleepValue);
            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " begin 第一阶段 " + (cyclicBarrier.getNumberWaiting() + 1));

            cyclicBarrier.await();

            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " end 第一阶段 ");

//            sleepValue = (long) (Math.random() * 10000);

            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " begin 第二阶段 " + (cyclicBarrier.getNumberWaiting() + 1));

            cyclicBarrier.await();

            System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " end 第二阶段 ");

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
