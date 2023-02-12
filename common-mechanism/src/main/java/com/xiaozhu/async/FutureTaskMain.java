package com.xiaozhu.async;

import java.util.concurrent.*;

public class FutureTaskMain {
    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("Thread executing....");
            Thread.sleep(1000);

            int num = 0;
            for(int i = 0; i < 10; i++) {
                num += i;
            }
            return num;
        }
    }
    public static void test() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        System.out.println(futureTask.get());
        service.submit(futureTask);
        service.shutdown();
    }

    public static void main(String[] args) {
        System.out.println("Start threading.....");
        FutureTask<String> task = new FutureTask<>(()->{

            Thread.sleep(5000);

            return "this is a callable.";
        });
//        task.run();
        new Thread(task).start();
        System.out.println("Wait to get result");
        try {
            test();
            System.out.println(task.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
