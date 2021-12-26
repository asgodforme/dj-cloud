package com.dj.cloud.test.juc.cyclicBarrier.run;

public class ThreadA extends Thread {

    private MyService myService;

    public ThreadA(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.beginRun();
    }
}
