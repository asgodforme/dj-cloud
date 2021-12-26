package com.dj.cloud.test.juc.phaser.test1;

import java.util.concurrent.Phaser;

public class Run {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        PrintTools.phaser = phaser;
        Thread a = new Thread(() -> PrintTools.methodA());
        a.start();
        Thread b = new Thread(() -> PrintTools.methodA());
        b.start();
        Thread c = new Thread(() -> PrintTools.methodB());
        c.start();
    }
}
