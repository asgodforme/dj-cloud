package com.dj.cloud.test.juc.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class MyService1 {
    private CountDownLatch down = new CountDownLatch(1);
    public void testMethod() {
        System.out.println(Thread.currentThread().getName() + "准备！");
        try {
            down.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    public void down() {
        System.out.println("begin");
        down.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        MyService1 m1 = new MyService1();
        MyThread2[] myThread = new MyThread2[10];
        for (int i = 0; i < myThread.length; i++) {
            myThread[i] = new MyThread2(m1);
            myThread[i].setName("线程" + i);
            myThread[i].start();
        }

        Thread.sleep(2000);

        m1.down();
    }
}

class MyThread2 extends Thread {
    private MyService1 myService1;

    public MyThread2(MyService1 myService1) {
        this.myService1 = myService1;
    }

    @Override
    public void run() {
        myService1.testMethod();
    }
}

