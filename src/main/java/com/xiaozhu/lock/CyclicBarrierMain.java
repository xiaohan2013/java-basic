package com.xiaozhu.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierMain {
    public CyclicBarrierMain() {
    }

    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            public void run() {
                System.out.println("Cyclic Barrier Full");
            }
        });
        ExecutorService pool = Executors.newFixedThreadPool(20);

        for(int i = 0; i < 10; ++i) {
            final int cnt = i;
            pool.execute(new Runnable() {
                public void run() {
                    System.out.println("=====>Start=== " + cnt);

                    try {
                        System.out.println("dooooooooooooooooooooooooooooooo");
                        cyclicBarrier.await();
                    } catch (InterruptedException var6) {
                        throw new RuntimeException(var6);
                    } catch (BrokenBarrierException var7) {
                        throw new RuntimeException(var7);
                    } finally {
                        System.out.println("=====>End=== " + cnt);
                    }

                }
            });
        }

        System.out.println("Finish!!!");
        pool.shutdown();
    }
}
