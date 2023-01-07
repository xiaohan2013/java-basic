package com.xiaozhu.services.leaderelection;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class LeaderLatchRunner implements Runnable {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    LeaderLatchRunner() {
    }

    public void run() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2182,127.0.0.1:2183,127.0.0.1:2184").retryPolicy(new ExponentialBackoffRetry(1000, 3)).connectionTimeoutMs(40000).sessionTimeoutMs(10000).namespace("xiaozhu").build();
        curatorFramework.start();

        assert curatorFramework.getState().equals(CuratorFrameworkState.STARTED);

        try {
            Thread.sleep((long)(new Random()).nextInt(1000));
        } catch (InterruptedException var5) {
            throw new RuntimeException(var5);
        }

        LeaderLatch latch = new LeaderLatch(curatorFramework, "/xiaozhu/leader", Thread.currentThread().getName(), LeaderLatch.CloseMode.NOTIFY_LEADER);
        latch.addListener((new LeaderLatchListener() {
            private LeaderLatch LATCH;

            public LeaderLatchListener setLATCH(LeaderLatch LATCH) {
                this.LATCH = LATCH;
                return this;
            }

            public void isLeader() {
                System.out.println("===========" + this.LATCH.getId() + "被选举为Leader");

                try {
                    this.LATCH.getParticipants().stream().forEach(System.out::println);
                    Thread.sleep(5000L);
                    this.LATCH.close();
                } catch (Exception var2) {
                    throw new RuntimeException(var2);
                }
            }

            public void notLeader() {
                System.out.println("--------------------------------" + this.LATCH.getId() + "离开，重新进行Leader选举--------------------------------");
            }
        }).setLATCH(latch), EXECUTOR_SERVICE);
        System.out.println(latch.getId() + "准备好了！");

        try {
            latch.start();
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }

        System.out.println(latch.getId() + "开始Leader选举！");
    }
}

