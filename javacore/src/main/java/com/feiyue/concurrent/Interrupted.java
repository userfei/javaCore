package com.feiyue.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 理解线程中断，创建两个线程，一个不停睡眠，一个一直运行，然后分别进行中断，判断他们的中断标识位
 * @author  feiyue
 * @date  2019/10/7
 */
public class Interrupted {

    public static void main(String[] args) throws Exception{
        // sleepThread 不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);

        // busyThread 不停的运行
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();

        // 休眠 5s，让 sleepThread 和 busyThread 充分运行
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();
        // 抛出异常，中断标识位被清除 false
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        // 运行的线程，中断标识位没有被清除 true
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
        SleepUtils.second(2);
    }

    private static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    private static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true){}
        }
    }
}
