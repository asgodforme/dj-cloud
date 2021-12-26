package com.dj.cloud.test.juc.futureCalllable;

import java.util.concurrent.*;

public class Test1 {

    static class MyCallable implements Callable<String> {

        private int age;

        public MyCallable(int age) {
            this.age = age;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(8000);
            return "返回的年龄是；" + age;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable(100);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        Future<String> submit = threadPoolExecutor.submit(myCallable);
        threadPoolExecutor.execute(() -> {});
        System.out.println("main A " + System.currentTimeMillis());
        System.out.println(submit.get());
        System.out.println("main B " + System.currentTimeMillis());
        threadPoolExecutor.shutdown();
    }
}
