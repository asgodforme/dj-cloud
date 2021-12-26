package com.dj.cloud.test.juc.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class MyService {
    private CountDownLatch down = new CountDownLatch(1);

    public void testMethod() {
        try {
            System.out.println("A");
            down.await();
            System.out.println("B");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downMethod() {
        System.out.println("X");
        down.countDown();
        System.out.println("after countDown");
    }

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        MyThread myThread = new MyThread(myService);
        myThread.start();

        Thread.sleep(2000);

        myService.downMethod();
    }
}

class MyThread extends Thread {
    private MyService myService;

    public MyThread(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }
}


