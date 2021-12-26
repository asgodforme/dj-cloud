package com.dj.cloud.test.juc.cyclicBarrier.run2;

public class MyThread extends Thread {

    private MyService myService;

    public MyThread(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testA();
    }
}
