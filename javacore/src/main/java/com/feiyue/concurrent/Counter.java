package com.feiyue.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 循环 CAS 操作实现原子操作
 * @author  feiyue
 * @date  2019/10/5
 */
public class Counter {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        final Counter counter = new Counter();
        List<Thread> threadList = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < 10000; i++) {
                        counter.count();
                        counter.safeCount();
                    }
                }
            });
            threadList.add(t);
        }
        for(Thread t : threadList) {
            t.start();
        }

        // 等待所有线程执行完毕
        for(Thread t : threadList) {
            try{
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(counter.i);
        System.out.println(counter.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    private void safeCount() {
        for(;;) {
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            if(suc) {
                break;
            }
        }
    }

    private void count() {
        i++;
    }
}
