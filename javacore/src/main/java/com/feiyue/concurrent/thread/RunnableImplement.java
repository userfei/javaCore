package com.feiyue.concurrent.thread;

/**
 * 线程的 Runnable 的实现
 * @author  feiyue
 * @date  2019/12/9
 */
public class RunnableImplement implements Runnable {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        Thread thread = new Thread(new RunnableImplement(), "threadName");
        thread.start();
    }
    @Override
    public void run() {
        System.out.println("Runnable implement");
    }
}
