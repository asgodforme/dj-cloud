package com.dj.cloud.test.juc.cyclicBarrier;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyThread1 extends Thread {
    private CyclicBarrier cbRef;
    public MyThread1(CyclicBarrier cbRef) {
        super();
        this.cbRef = cbRef;
    }

    @Override
    public void run() {
        try {
            System.out.println("等待凑齐2个");
            Thread.sleep((long) (Math.random() * 1000));
            cbRef.await();
            System.out.println(Thread.currentThread().getName() + "到了" + System.currentTimeMillis());
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cy = new CyclicBarrier(2, () -> {
            System.out.println("全部到了");
        });
        MyThread1[] myThreads =new MyThread1[4];
        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i] = new MyThread1(cy);
            myThreads[i].start();
            Thread.sleep(2000);
        }
    }
}
