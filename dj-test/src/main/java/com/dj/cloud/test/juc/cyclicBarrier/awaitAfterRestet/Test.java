package com.dj.cloud.test.juc.cyclicBarrier.awaitAfterRestet;

import java.util.concurrent.CyclicBarrier;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        ThreadA t1 = new ThreadA(cyclicBarrier);
        t1.start();
        Thread.sleep(500);
        System.out.println(cyclicBarrier.getNumberWaiting());

        ThreadA t2 = new ThreadA(cyclicBarrier);
        t2.start();
        Thread.sleep(500);
        System.out.println(cyclicBarrier.getNumberWaiting());

        ThreadA t3 = new ThreadA(cyclicBarrier);
        t3.start();
        Thread.sleep(500);
        System.out.println(cyclicBarrier.getNumberWaiting());

        ThreadA t4 = new ThreadA(cyclicBarrier);
        t4.start();
        Thread.sleep(500);
        System.out.println(cyclicBarrier.getNumberWaiting());


    }
}
