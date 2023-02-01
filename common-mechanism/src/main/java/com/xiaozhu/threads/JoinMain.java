package com.xiaozhu.threads;

public class JoinMain {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Current Thread = " + Thread.currentThread().getName());
            }
        };

        Thread thread1 = new Thread(runnable, "Thread-1");
        Thread thread2 = new Thread(runnable, "Thread-2");

        // 处于开始状态，但还未被调度执行。RUNNABLE
        thread1.start();
        thread2.start();

        try{
            // RUNNING
            thread2.join();
            thread1.join();
            // 等价于
//        while (thread1.isAlive() || thread2.isAlive()) {
//            //只要两个线程中有任何一个线程还在活动，主线程就不会往下执行
//        }
        } catch (InterruptedException e) {
            thread2.interrupt();
            thread1.interrupt();
            System.err.println(e.getStackTrace());
        }




        System.out.println("Finished.");
    }
}
