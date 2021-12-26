package com.dj.cloud.test.juc.cyclicBarrier.run5;


public class Run {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        MyThread myThread = new MyThread(myService);
        myThread.setName("A");

        MyThread myThread1 = new MyThread(myService);
        myThread1.setName("B");

        MyThread myThread2 = new MyThread(myService);
        myThread2.setName("C");

        myThread.start();
        myThread1.start();
        myThread2.start();

        Thread.sleep(2000);
        myService.cyclicBarrier.reset();
    }
}
