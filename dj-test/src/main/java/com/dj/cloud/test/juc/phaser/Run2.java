package com.dj.cloud.test.juc.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Run2 {
    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(3);
        Thread a = new Thread(() -> {
            System.out.println("1");
            try {
                phaser.awaitAdvanceInterruptibly(0, 5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println("2");
        });

        a.start();




        Thread.sleep(1000);
        a.interrupt();
//        phaser.arrive();

//
//        Thread.sleep(1000);
//        phaser.arrive();
//
//        Thread.sleep(1000);
//        phaser.arrive();
//
//        System.out.println("aaaa");
    }
}
