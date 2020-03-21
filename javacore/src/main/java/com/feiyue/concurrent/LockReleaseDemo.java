package com.feiyue.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 测试对象锁释放
 * @author  feiyue
 * @date  2019/10/7
 */
public class LockReleaseDemo {

    public static void main(String[] args) throws Exception{
        Thread waitThread1 = new Thread(new Wait(),  "WaitThread1");
        Thread waitThread2 = new Thread(new Wait(),  "WaitThread2");
        waitThread1.start();
        waitThread2.start();

        Thread sleepThread1 = new Thread(new Sleep(),  "SleepThread1");
        Thread sleepThread2 = new Thread(new Sleep(),  "SleepThread2");
        sleepThread1.start();
        sleepThread2.start();
    }

    private static class Wait implements Runnable {
        @Override
        public void run() {
            synchronized (Wait.class) {
                System.out.println("wait thread enter, name=" + Thread.currentThread().getName());
                try {
                    Wait.class.wait(10000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Sleep implements Runnable {
        @Override
        public void run() {
            synchronized (Sleep.class) {
                System.out.println("Sleep thread enter, name=" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
