package com.xiaozhu.collection;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class QueueMain {
    public static void main(String[] args) throws InterruptedException {
        //
        BlockingDeque bq = new LinkedBlockingDeque();
        // add
        // take
        bq.add(1);
        bq.take();
    }
}
