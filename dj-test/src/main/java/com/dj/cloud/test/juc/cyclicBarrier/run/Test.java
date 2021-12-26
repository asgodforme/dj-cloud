package com.dj.cloud.test.juc.cyclicBarrier.run;

import java.util.concurrent.CyclicBarrier;

public class Test {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        MyService myService = new MyService(cyclicBarrier);

        ThreadA t1 = new ThreadA(myService);
        t1.setName("A");
        t1.start();

        ThreadA t2 = new ThreadA(myService);
        t2.setName("B");
        t2.start();

        ThreadA t3 = new ThreadA(myService);
        t3.setName("C");
        t3.start();

        ThreadA t4 = new ThreadA(myService);
        t4.setName("D");
        t4.start();

    }
}
