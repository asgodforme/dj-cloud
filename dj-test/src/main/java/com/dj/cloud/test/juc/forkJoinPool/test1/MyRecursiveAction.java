package com.dj.cloud.test.juc.forkJoinPool.test1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MyRecursiveAction extends RecursiveAction {

    private int beginValue;
    private int endValue;

    public MyRecursiveAction(int beginValue, int endValue) {
        this.beginValue = beginValue;
        this.endValue = endValue;
    }

    @Override
    protected void compute() {
        System.out.println(Thread.currentThread().getName() + "-----------------------");
        if (endValue -beginValue > 2) {
            int middleNum = (beginValue + endValue) / 2;
            MyRecursiveAction left = new MyRecursiveAction(beginValue, middleNum);
            MyRecursiveAction right = new MyRecursiveAction(middleNum + 1, endValue);
            invokeAll(left, right);
        } else {
            System.out.println("打印组合" + beginValue + "____" + endValue);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new MyRecursiveAction(1, 10));
        Thread.sleep(5000);
    }
}
