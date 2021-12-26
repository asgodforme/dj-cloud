package com.dj.cloud.test.juc.phaser.test3;

import java.util.concurrent.Phaser;

public class MyService {
    private Phaser phaser;

    public MyService(Phaser phaser) {
        this.phaser = phaser;
    }

    public void testMethod() {
        try {
            System.out.println("A begin threadname: " + Thread.currentThread().getName() + ", " + System.currentTimeMillis());
            if (Thread.currentThread().getName().equals("B")) {
                Thread.sleep(5000);
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println("phaser value: " + phaser.getPhase() + "A begin threadname: " + Thread.currentThread().getName() + ", " + System.currentTimeMillis());

            System.out.println("B begin threadname: " + Thread.currentThread().getName() + ", " + System.currentTimeMillis());
            if (Thread.currentThread().getName().equals("B")) {
                Thread.sleep(5000);
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println("phaser value: " + phaser.getPhase() + "B begin threadname: " + Thread.currentThread().getName() + ", " + System.currentTimeMillis());

            System.out.println("C begin threadname: " + Thread.currentThread().getName() + ", " + System.currentTimeMillis());
            if (Thread.currentThread().getName().equals("B")) {
                Thread.sleep(5000);
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println("phaser value: " + phaser.getPhase() + "C begin threadname: " + Thread.currentThread().getName() + ", " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
