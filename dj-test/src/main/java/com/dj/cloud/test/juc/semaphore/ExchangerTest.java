package com.dj.cloud.test.juc.semaphore;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExchangerTest {

    static class ThreadA extends Thread {
        private Exchanger<String> exchanger;

        public ThreadA(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + "get "
                        + exchanger.exchange("我是线程" + Thread.currentThread().getName(), 1000, TimeUnit.MICROSECONDS));
                System.out.println("A end");
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        ThreadA threadA = new ThreadA(exchanger);
        threadA.setName("A");
        threadA.start();


//        ThreadA threadB = new ThreadA(exchanger);
//        threadB.setName("B");
//        threadB.start();

    }
}
