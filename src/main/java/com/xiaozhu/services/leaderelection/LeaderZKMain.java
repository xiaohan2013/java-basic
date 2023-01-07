package com.xiaozhu.services.leaderelection;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeaderZKMain {
    public static void main(String[] args) throws IOException {
        String zkConnectionString = "127.0.0.1:2182,127.0.0.1:2183,127.0.0.1:2184";
        String LEAD_PATH = "/xiaozhu/leader";
        List<CuratorFramework> clients = Lists.newArrayList();
        List<CandidatorClient> candidates = Lists.newArrayList();
        boolean var12 = false;

        CuratorFramework client;
        try {
            var12 = true;
            int i = 0;

            while(true) {
                if (i >= 10) {
                    System.out.println("Press enter/return to quit\n");
                    (new BufferedReader(new InputStreamReader(System.in))).readLine();
                    var12 = false;
                    break;
                }

                client = CuratorFrameworkFactory.newClient(zkConnectionString, new ExponentialBackoffRetry(1000, 10));
                clients.add(client);
                CandidatorClient candidate = new CandidatorClient(client, "/xiaozhu/leader", "Client #" + i);
                candidates.add(candidate);
                client.start();
                candidate.start();
                ++i;
            }
        } finally {
            if (var12) {
                System.out.println("Shutting down...");
                Iterator var9 = candidates.iterator();

                while(var9.hasNext()) {
                    CandidatorClient candidate = (CandidatorClient)var9.next();
                    CloseableUtils.closeQuietly(candidate);
                }

                var9 = clients.iterator();

                while(var9.hasNext()) {
                    client = (CuratorFramework)var9.next();
                    CloseableUtils.closeQuietly(client);
                }

            }
        }

        System.out.println("Shutting down...");
        Iterator var14 = candidates.iterator();

        while(var14.hasNext()) {
            CandidatorClient candidate = (CandidatorClient)var14.next();
            CloseableUtils.closeQuietly(candidate);
        }

        var14 = clients.iterator();

        while(var14.hasNext()) {
            client = (CuratorFramework)var14.next();
            CloseableUtils.closeQuietly(client);
        }

    }
}
