package com.dj.cloud.test.juc.phaser.test5;

import java.util.concurrent.Phaser;

public class MyThread extends Thread{
    private Phaser phaser;

    public MyThread(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " A1 begin = " + System.currentTimeMillis());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + " A1 end = " + System.currentTimeMillis());
    }

    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(7);
        MyThread[] ts = new MyThread[5];
        for (int i = 0; i < 5; i++) {
            ts[i]  = new MyThread(phaser);
            ts[i].setName("thread" + i);
            ts[i].start();
        }
        Thread.sleep(2000);
        System.out.println("已到达：" + phaser.getArrivedParties());
        System.out.println("no arrive : " + phaser.getUnarrivedParties());
    }
}
