package com.xiaozhu.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ExecutorMain {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(Thread.currentThread().getThreadGroup(), r, "Checkpoint Timer");
                t.setDaemon(false);
                t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){

                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        System.err.println("FATAL: Thread '{}' produced an uncaught exception. Stopping the process..." + t.getName());
                        System.err.println(e);
                    }
                });
                return t;
            }
        });

        timer.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId() + "=" + Thread.currentThread().getName() );
            }
        }, 10, 2, TimeUnit.SECONDS);


        Thread.sleep(100000000);
    }
}
