package com.dj.cloud.test.juc.phaser.test7;

import java.util.concurrent.Phaser;

public class MyService {

    private Phaser phaser;

    public MyService(Phaser phaser) {
        this.phaser = phaser;
    }

    public void testA() {
        try {
            System.out.println(Thread.currentThread().getName() + " 1 begin");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " " + phaser.getArrivedParties());
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " 1 end");

            System.out.println(Thread.currentThread().getName() + " 2 begin");
            Thread.sleep(3000);
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " 2 end");

            System.out.println(Thread.currentThread().getName() + " 3 begin");
            Thread.sleep(3000);
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + " 3 end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testB() {
        System.out.println(Thread.currentThread().getName() + " 1 begin");
        phaser.arrive();
        System.out.println(Thread.currentThread().getName() + " 1 end");

        System.out.println(Thread.currentThread().getName() + " 2 begin");
        phaser.arrive();
        System.out.println(Thread.currentThread().getName() + " 2 end");

        System.out.println(Thread.currentThread().getName() + " 3 begin");
        phaser.arrive();
        System.out.println(Thread.currentThread().getName() + " 3 end");
        System.out.println(Thread.currentThread().getName() + " " + phaser.getArrivedParties());
    }


}
