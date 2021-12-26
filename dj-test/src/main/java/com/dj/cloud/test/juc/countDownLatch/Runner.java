package com.dj.cloud.test.juc.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class Runner extends Thread {

    private CountDownLatch comingTag;
    private CountDownLatch waitTag;
    private CountDownLatch waitRunTag;
    private CountDownLatch beginTag;
    private CountDownLatch endTag;

    public Runner(CountDownLatch comingTag, CountDownLatch waitTag, CountDownLatch waitRunTag, CountDownLatch beginTag, CountDownLatch endTag) {
        this.comingTag = comingTag;
        this.waitTag = waitTag;
        this.waitRunTag = waitRunTag;
        this.beginTag = beginTag;
        this.endTag = endTag;
    }

    @Override
    public void run() {

        try {
            System.out.println("运动员使用不同的交通工具到达。");
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println(Thread.currentThread().getName() + "达到起点");
            comingTag.countDown();
            System.out.println("等待裁判说准备！");
            waitTag.await();
            System.out.println("各就各位，准备起跑姿势！");
            Thread.sleep((long) (Math.random() * 10000));
            waitRunTag.countDown();
            beginTag.await();
            System.out.println(Thread.currentThread().getName() + "起跑，用时不确定。");
            Thread.sleep((long) (Math.random() * 10000));
            endTag.countDown();
            System.out.println(Thread.currentThread().getName() + "到达终点");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch comingTag = new CountDownLatch(10);
        CountDownLatch waitTag = new CountDownLatch(1);
        CountDownLatch waitRunTag = new CountDownLatch(10);
        CountDownLatch beginTag = new CountDownLatch(1);
        CountDownLatch endTag = new CountDownLatch(10);

        Runner[] runners = new Runner[10];
        for (int i = 0; i < runners.length; i++) {
            runners[i] = new Runner(comingTag, waitTag, waitRunTag, beginTag, endTag);
            runners[i].start();
        }

        System.out.println("裁判在等待选手到来！");
        comingTag.await();
        System.out.println("所有选手都到了。");
        Thread.sleep(5000);
        waitTag.countDown();
        System.out.println("各就各位！");
        waitRunTag.await();
        System.out.println("发令枪响起");
        beginTag.countDown();
        endTag.await();
        System.out.println("所有的运动员都到达终点");

    }
}
