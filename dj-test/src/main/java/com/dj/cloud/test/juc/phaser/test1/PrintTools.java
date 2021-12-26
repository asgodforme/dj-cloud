package com.dj.cloud.test.juc.phaser.test1;

import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import java.util.concurrent.Phaser;

public class PrintTools {
    public static Phaser phaser;

    public static void methodA() {
        System.out.println(Thread.currentThread().getName() + " A1 begin = " + System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + " A1 end = " + System.currentTimeMillis());

        System.out.println(Thread.currentThread().getName() + " A2 begin = " + System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + " A2 end = " + System.currentTimeMillis());
    }

    public static void methodB() {
        try {
            System.out.println(Thread.currentThread().getName() + " A1 begin = " + System.currentTimeMillis());
            Thread.sleep(5000);
//            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndDeregister();
            System.out.println(Thread.currentThread().getName() + " A1 end = " + System.currentTimeMillis());


//            System.out.println(Thread.currentThread().getName() + " A2 begin = " + System.currentTimeMillis());
//            Thread.sleep(5000);
//            phaser.arriveAndAwaitAdvance();
//            System.out.println(Thread.currentThread().getName() + " A2 end = " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
