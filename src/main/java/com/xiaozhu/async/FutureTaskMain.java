package com.xiaozhu.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskMain {
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
            System.out.println(task.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
