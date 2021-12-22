package com.dj.cloud.test.juc.semaphore;

import java.util.concurrent.Semaphore;

public class Service1 {
    private Semaphore semaphore = new Semaphore(2);

    public void testMethod() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " begin time: " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " end time: " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    static class ThreadA extends Thread {
        private Service1 service;

        public ThreadA(Service1 service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.testMethod();
        }
    }

    static class ThreadB extends Thread {
        private Service1 service;

        public ThreadB(Service1 service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.testMethod();
        }
    }

    public static void main(String[] args) {
        Service1 service = new Service1();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("A");
        ThreadB threadB = new ThreadB(service);
        threadB.setName("B");
        threadA.start();
        threadB.start();

    }
}
