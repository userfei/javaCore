package com.feiyue.concurrent;

/**
 * 线程状态
 * @author  feiyue
 * @date  2019/9/7
 */
public class ThreadState {
    
    public static void main(String[] args) {
        Thread thread = new Thread(new TimeWaiting(), "TimeWaitingThread");
        thread.setPriority(7);
        thread.start();
        new Thread(new Waiting(), "WaitingThread").start();

        // 两个线程 Blocked，一个获取锁成功，一个阻塞
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
    }

    static class TimeWaiting implements Runnable {

        @Override
        public void run() {
            while(true) {
                SleepUtils.second(100);
            }
        }
    }

    static class Waiting implements Runnable {
        @Override
        public void run() {
            while(true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable {
        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    SleepUtils.second(100);
                }
            }
        }
    }
}
