package com.xiaozhu.services.leaderelection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LeaderLatchMain {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    public LeaderLatchMain() {
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 7; ++i) {
            EXECUTOR_SERVICE.execute(new LeaderLatchRunner());
        }

        Thread.sleep(1000000000L);
    }
}
