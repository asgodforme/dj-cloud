package com.dj.cloud.test.juc.phaser.test8;

import java.util.concurrent.Phaser;

public class Run {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        Thread a = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println("a arrive");
        }, "a");

        Thread b = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println("b arrive");
        }, "b");

        Thread c = new Thread(() -> {
            System.out.println("c: "+ phaser.getPhase());
            phaser.awaitAdvance(1);
            System.out.println("c arrive");
        }, "c");

        Thread d = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println("d arrive");
        }, "d");

        c.start();
        a.start();
        b.start();
        d.start();

    }
}
