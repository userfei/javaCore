package com.feiyue.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerDemo {

    private static final Exchanger<String> exgr = new Exchanger<>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "haha";

                    // 将当前线程数据A存入交换器中，并取出`被交换线程`的存入的数据
                    String B = exgr.exchange(A);
                    System.out.println("A 是否和 B 数据一致：" + A.equals(B));  // true
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "银行流水B";
                    String A = exgr.exchange("haha");
                    System.out.println("A 是否和 B 数据一致：" + A.equals(B));  // false
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.shutdown();
    }
}
