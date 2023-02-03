package com.xiaozhu.lock;

import org.apache.commons.lang3.RandomUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockMain {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    // 读-读：共享锁
    public static void test1(){
        for (int i = 0; i < 3; i++) {
            Runnable task = new ReadTask(lock, "Read Task #i" + i);
            threadPool.execute(task);
        }

        // 主线程等待所有任务执行完毕
        try {
            Thread.sleep(1000*10);
        } catch (Exception e) {

        }
    }
    /**
     * 测试: 写锁为互斥锁
     */
    public void test2() {
        System.out.println("\n---------------------- Test 2 ----------------------");
        for (int i=0; i<3; i++) {
            Runnable task = new WriteTask( lock, "写任务 #"+i );
            threadPool.execute(  task );
        }
        // 主线程等待所有任务执行完毕
        try{ Thread.sleep( 10*1000 ); } catch (Exception e) {}
    }

    /**
     * 测试: 读写互斥
     */
    public void test3() {
        System.out.println("\n---------------------- Test 3 ----------------------");
        for (int i=0; i<8; i++) {
            Runnable task = null;
            Boolean isReadTask = RandomUtils.nextBoolean();
            if( isReadTask ) {
                task = new ReadTask( lock, "读任务 #"+i );
            } else {
                task = new WriteTask( lock, "写任务 #"+i );
            }
            threadPool.execute( task );
        }
        // 主线程等待所有任务执行完毕
        try{ Thread.sleep( 50*1000 ); } catch (Exception e) {}
    }
    public static void main(String[] args) {
        test1();
    }
    /**
     * 打印信息
     * @param msg
     */
    public static void info(String msg) {
        String time = formatter.format(LocalTime.now());
        String thread = Thread.currentThread().getName();
        String log = "["+time+"] "+ " <"+ thread +"> " + msg;
        System.out.println(log);
    }

    private static class ReadTask implements Runnable {
        private ReentrantReadWriteLock lock;
        private String name;

        public ReadTask(ReentrantReadWriteLock lock, String name) {
            this.lock = lock;
            this.name = name;
        }
        @Override
        public void run() {
            // 获取读锁
            ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
            readLock.lock();
            info(name+ ": 成功获取读锁");
            try {
                // 模拟业务耗时
                Thread.sleep(RandomUtils.nextLong(1000, 3000));
            } catch (Exception e) {
                System.out.println( "Happen Exception: " + e.getMessage());
            } finally {
                info(name+ ": 释放读锁");
                readLock.unlock();
            }
        }
    }

    private static class WriteTask implements Runnable{

        private ReentrantReadWriteLock lock;

        private String name;
        public WriteTask(ReentrantReadWriteLock lock, String name) {
            this.lock = lock;
            this.name = name;
        }
        @Override
        public void run() {
            // 获取写锁
            ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
            writeLock.lock();
            info(name+ ": 成功获取写锁");
            try {
                // 模拟业务耗时
                Thread.sleep(RandomUtils.nextLong(1000, 3000));
            } catch (Exception e) {
                System.out.println( "Happen Exception: " + e.getMessage());
            } finally {
                info(name+ ": 释放写锁");
                writeLock.unlock();
            }
        }
    }

}
