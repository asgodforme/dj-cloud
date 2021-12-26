package com.dj.cloud.test.juc.cyclicBarrier;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyThread extends Thread {
    private CyclicBarrier cbRef;
    public MyThread(CyclicBarrier cbRef) {
        super();
        this.cbRef = cbRef;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println(Thread.currentThread().getName() + "到了" + System.currentTimeMillis());
            cbRef.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cy = new CyclicBarrier(5, () -> {
            System.out.println("全部到了");
        });
        MyThread[] myThreads =new MyThread[5];
        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i] = new MyThread(cy);
        }
        Arrays.asList(myThreads).stream().forEach(Thread::start);
    }
}
