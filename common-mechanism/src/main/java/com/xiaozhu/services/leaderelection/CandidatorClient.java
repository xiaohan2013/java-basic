package com.xiaozhu.services.leaderelection;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CandidatorClient extends LeaderSelectorListenerAdapter implements Closeable {
    private final String name;
    private final LeaderSelector leaderSelector;
    private final AtomicInteger leaderCount = new AtomicInteger();

    public CandidatorClient(CuratorFramework client, String path, String name) {
        this.name = name;
        this.leaderSelector = new LeaderSelector(client, path, this);
        this.leaderSelector.autoRequeue();
    }

    public void start() throws IOException {
        this.leaderSelector.start();
    }

    public void close() throws IOException {
        this.leaderSelector.close();
    }

    public void takeLeadership(CuratorFramework client) throws Exception {
        int waitSeconds = (int)(5.0 * Math.random() + 1.0);
        System.out.println(this.name + " is now the leader. Waiting " + waitSeconds + " seconds...");
        System.out.println(this.name + " has been leader " + this.leaderCount.getAndIncrement() + " time(s) before.");

        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis((long)waitSeconds));
        } catch (InterruptedException var7) {
            System.err.println(this.name + " was interrupted.");
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(this.name + " relinquishing leadership.\n");
        }

    }
}
