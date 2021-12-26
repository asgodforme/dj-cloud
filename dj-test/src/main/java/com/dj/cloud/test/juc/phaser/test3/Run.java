package com.dj.cloud.test.juc.phaser.test3;

import java.util.concurrent.Phaser;

public class Run {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(2) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("onadvance 被调用");
                return false;
            }
        };
        MyService myService = new MyService(phaser);
        Thread a = new Thread(() -> myService.testMethod(), "A");
        Thread b = new Thread(() -> myService.testMethod(), "B");

        a.start();
        b.start();
    }
}
