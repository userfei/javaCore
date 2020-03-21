package com.feiyue.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    // CountDownLatch 初始计数为 2，当 计数为 0 时，其 await() 方法才不会阻塞
    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException{

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                c.countDown();  // 每调用一次 countDown, 其计数器减少 1
                System.out.println(2);
                c.countDown();
            }
        });
        t1.start();
        c.await();
        System.out.println(3);
    }
}
