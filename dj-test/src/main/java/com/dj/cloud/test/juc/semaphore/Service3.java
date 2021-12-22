package com.dj.cloud.test.juc.semaphore;

import java.util.concurrent.Semaphore;

public class Service3 {
    private Semaphore semaphore = new Semaphore(1);

    public void testMethod() {
        try {
            semaphore.acquireUninterruptibly(1);
            System.out.println(Thread.currentThread().getName() + " begin time: " + System.currentTimeMillis());
            System.out.println(Thread.currentThread().isInterrupted());
//            Thread.sleep(10000);
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("1");
            }
            System.out.println(Thread.currentThread().isInterrupted());
//            System.out.println(Thread.currentThread().getName() + " end time: " + System.currentTimeMillis());
//        } catch (InterruptedException e) {
//            System.out.println(Thread.currentThread().getName() + "被中断");
//            System.out.println(Thread.currentThread().isInterrupted());
//            e.printStackTrace();
        } finally {
            semaphore.release(1);
        }
    }

    static class ThreadA extends Thread {
        private Service3 service;

        public ThreadA(Service3 service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.testMethod();
        }
    }

    static class ThreadB extends Thread {
        private Service3 service;

        public ThreadB(Service3 service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.testMethod();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Service3 service = new Service3();
//        ThreadA threadA = new ThreadA(service);
//        threadA.setName("A");
        ThreadB threadB = new ThreadB(service);
        threadB.setName("B");
//        threadA.start();
        threadB.start();

        Thread.sleep(1000);
        threadB.interrupted();
        Thread.sleep(5000);
        System.out.println(threadB.isInterrupted());


    }
}
