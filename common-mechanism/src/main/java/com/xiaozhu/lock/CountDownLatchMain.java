package com.xiaozhu.lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchMain {
    public CountDownLatchMain() {
    }

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(20);
        ExecutorService pool = Executors.newFixedThreadPool(20);

        for(int i = 0; i < 20; ++i) {
            final int cnt = i;
            pool.execute(new Runnable() {
                public void run() {
                    int starCnt = (new Random()).nextInt(20);
                    System.out.println("====>>>>>>>  " + String.valueOf(cnt) + " = " + starCnt + " ");

                    for(StringBuilder sb = new StringBuilder(); starCnt != 0; --starCnt) {
                        sb.append("-");
                        System.out.print("\n" + cnt + ">>" + sb.toString());

                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException var4) {
                            throw new RuntimeException(var4);
                        }
                    }

                    latch.countDown();
                }
            });
        }

        latch.await();
        pool.shutdown();
        System.out.println("=================================Finish.");
    }
}
