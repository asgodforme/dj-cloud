package com.dj.cloud.test.juc.phaser.test2;

import java.util.concurrent.Phaser;

public class Run {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        ThreadA t = new ThreadA(phaser);
        t.start();
    }
}
