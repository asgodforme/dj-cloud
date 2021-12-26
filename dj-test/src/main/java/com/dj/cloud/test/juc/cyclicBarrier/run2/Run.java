package com.dj.cloud.test.juc.cyclicBarrier.run2;

import java.util.concurrent.CyclicBarrier;

public class Run {
    public static void main(String[] args) {
        int parties = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(parties, () -> {
            System.out.println("都到了");
        });

        MyService myService = new MyService(cyclicBarrier);

        MyThread[] ts = new MyThread[4];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new MyThread(myService);
            ts[i].start();
        }
    }
}
