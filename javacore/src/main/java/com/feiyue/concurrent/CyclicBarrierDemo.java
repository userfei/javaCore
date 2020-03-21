package com.feiyue.concurrent;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    // 设置屏障拦截线程数为 2，当有两个线程调用 c.await() 则屏障打开
    static CyclicBarrier c = new CyclicBarrier(2);

    // CyclicBarrier(int, Runnable) 最后线程到达屏障，优先执行 Runnable 线程的 run 方法
    static CyclicBarrier priorityBarrier = new CyclicBarrier(2, new A());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();  // 当前线程到达屏障 c, 然后线程阻塞
                } catch (Exception e) {}
                System.out.println(1);
            }
        }).start();

        try {
            c.await();
        } catch (Exception e) {}
        System.out.println(2);
    }

    static class A implements Runnable {

        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
