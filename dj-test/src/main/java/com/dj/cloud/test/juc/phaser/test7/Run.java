package com.dj.cloud.test.juc.phaser.test7;


import java.util.concurrent.Phaser;

public class Run {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        MyService myService = new MyService(phaser);

        Thread a = new Thread(() -> myService.testA(), "a");
        Thread b = new Thread(() -> myService.testA(), "b");
        Thread c = new Thread(() -> myService.testB(), "c");

        a.start();
        b.start();
        c.start();
    }
}
