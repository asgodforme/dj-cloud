package com.dj.cloud.test.juc.semaphore;

import java.util.concurrent.Semaphore;

public class Service2 {
    private Semaphore semaphore = new Semaphore(10);

    public void testMethod() {
        try {
            semaphore.acquire(2);
            System.out.println(Thread.currentThread().getName() + " begin time: " + System.currentTimeMillis());
            int sleepValue = (int) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() + " sleep : " + sleepValue);
            Thread.sleep(sleepValue);
            System.out.println(Thread.currentThread().getName() + " end time: " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(2);
        }
    }

    static class ThreadA extends Thread {
        private Service2 service;

        public ThreadA(Service2 service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.testMethod();
        }
    }

    static class ThreadB extends Thread {
        private Service2 service;

        public ThreadB(Service2 service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.testMethod();
        }
    }

    public static void main(String[] args) {
        Service2 service = new Service2();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("A");
        ThreadB threadB = new ThreadB(service);
        threadB.setName("B");
        threadA.start();
        threadB.start();

    }
}
