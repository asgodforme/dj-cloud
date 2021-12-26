package com.dj.cloud.test.juc.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class MyThread1 extends Thread {
    private CountDownLatch maxRunner;

    public MyThread1(CountDownLatch maxRunner) {
        this.maxRunner = maxRunner;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName());
            maxRunner.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch maxRunner = new CountDownLatch(10);
        MyThread1[] tArray = new MyThread1[Integer.parseInt(maxRunner.getCount() + "")];
        for (int i = 0; i < tArray.length; i++) {
            tArray[i] = new MyThread1(maxRunner);
            tArray[i].setName("线程" + i);
            tArray[i].start();
        }
        maxRunner.await();
        System.out.println("都回来了 ");
    }
}
