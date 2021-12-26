package com.dj.cloud.test.juc.completionService.test1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Test1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        MyCallable username1 = new MyCallable("username1", 5000);
        MyCallable username2 = new MyCallable("username2", 4000);
        MyCallable username3 = new MyCallable("username3", 3000);
        MyCallable username4 = new MyCallable("username4", 2000);
        MyCallable username5 = new MyCallable("username5", 1000);

        List<Callable<String>> callableList = new ArrayList<>();

        callableList.add(username1);
        callableList.add(username2);
        callableList.add(username3);
        callableList.add(username4);
        callableList.add(username5);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        CompletionService csrf = new ExecutorCompletionService(threadPoolExecutor);
        for (int i = 0 ; i < 5; i++) {
            csrf.submit(callableList.get(i));
        }

        for (int i = 0; i < 6; i++) {
            System.out.println("等待打印第" + (i+1) + "个返回值");
            System.out.println(csrf.take().get());
        }
    }
}
