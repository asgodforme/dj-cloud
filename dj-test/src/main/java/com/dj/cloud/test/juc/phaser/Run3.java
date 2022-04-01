package com.dj.cloud.test.juc.phaser;

import java.util.concurrent.Phaser;

public class Run3 {
    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(3);
        phaser.register();
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "aaaaa");
            System.out.println(Thread.currentThread().getName() + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            System.out.println("bbbbb");
            phaser.arriveAndAwaitAdvance();
            System.out.println("ccccc");
        });
        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "aaaaa");
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + phaser.getPhase());
            System.out.println("bbbbb");
            phaser.arriveAndAwaitAdvance();
            System.out.println("ccccc");
        });
        Thread c = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "aaaaa");
            System.out.println(Thread.currentThread().getName() + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            System.out.println("bbbbb");
            phaser.arriveAndAwaitAdvance();
            System.out.println("ccccc");
        });
        a.start();
        b.start();
        c.start();

//        Thread.sleep(5000);

//        phaser.arriveAndDeregister();

    }
}
