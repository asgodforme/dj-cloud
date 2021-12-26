package com.dj.cloud.test.juc.phaser.test4;

import java.util.concurrent.Phaser;

public class Run {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(5);
        System.out.println("phaser.getRegisteredParties: " + phaser.getRegisteredParties());

        phaser.register();
        System.out.println("phaser.getRegisteredParties: " + phaser.getRegisteredParties());

        phaser.register();
        System.out.println("phaser.getRegisteredParties: " + phaser.getRegisteredParties());
        phaser.register();
        System.out.println("phaser.getRegisteredParties: " + phaser.getRegisteredParties());

        phaser.bulkRegister(10);
        System.out.println("phaser.getRegisteredParties: " + phaser.getRegisteredParties());

    }
}
