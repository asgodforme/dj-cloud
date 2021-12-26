package com.dj.cloud.test.juc.phaser.test9;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(3);
        Thread thread = new Thread(() -> {
            try {
                phaser.awaitAdvanceInterruptibly(0, 1, TimeUnit.SECONDS);
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
        });

        thread.start();
//        Thread.sleep(5000);
//
//        thread.interrupt();
//        System.out.println("interrupt");

    }
}
