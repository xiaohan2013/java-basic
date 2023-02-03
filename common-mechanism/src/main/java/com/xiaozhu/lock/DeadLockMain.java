package com.xiaozhu.lock;

public class DeadLockMain {
    public static void main(String[] args) {
        Object lock_a = new Object();
        Object lock_b = new Object();

        // synchronzed只有在代码块执行完才会释放锁
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock_a) {
                    // 先让T2获得lock_b
                    try { Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock_b){

                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable(){

            @Override
            public void run() {
                synchronized (lock_b) {
                    synchronized (lock_a) {

                    }
                }
            }
        });
    }
}
