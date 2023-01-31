package com.xiaozhu.services.clustermanager.impl;

import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.shareddata.Lock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

import java.util.concurrent.ExecutorService;

public class ZKLock implements Lock {

    private static final Logger log = LoggerFactory.getLogger(ZKLock.class);

    private final InterProcessSemaphoreMutex lock;
    private final ExecutorService lockReleaseExec;

    public ZKLock(InterProcessSemaphoreMutex lock, ExecutorService lockReleaseExec) {
        this.lock = lock;
        this.lockReleaseExec = lockReleaseExec;
    }

    public InterProcessSemaphoreMutex getLock() {
        return lock;
    }

    @Override
    public void release() {
        lockReleaseExec.execute(() -> {
            try {
                lock.release();
            } catch (Exception e) {
                log.error(e);
            }
        });
    }

}
