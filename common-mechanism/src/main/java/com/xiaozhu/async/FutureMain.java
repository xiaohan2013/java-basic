package com.xiaozhu.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FutureMain {

    static void testSupplyAsnc(){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() ->{
            System.out.println("compute test");
            return "test";
        });

        String result = future.join();
        System.out.println("get result: " + result);
    }

    static void testRunAsync(){
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("compute test");
        });

        System.out.println("get result" + future.join());
    }

    static void testDep() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute1");
            return 1;
        });

        CompletableFuture<Integer> future2 = future1.thenApply((p) -> {
            System.out.println("compute 2");
            return p + 10;
        });

        System.out.println("result : " + future2.get());
    }

    static void testWhencomplete(){
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("compute 1");
            return 1;
        });

        CompletableFuture future2 = future1.whenComplete((r , e) -> {
            if(e != null) {
                System.out.println("compute failed");
            } else  {
                System.out.println("received result is " + r);
            }
        });

        System.out.println("result: " + future2.join());
    }

    public static void main(String[] args) {

//        testSupplyAsnc();

//        testRunAsync();

        testWhencomplete();
    }

}
