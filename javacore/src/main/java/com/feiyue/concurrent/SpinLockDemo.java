package com.feiyue.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 手动实现简单自旋锁
 * @author  feiyue
 * @date  2019/12/7
 */
public class SpinLockDemo {

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        new Thread(() -> {
            spinLock.lock();
            try {
                System.out.println("thread_A execute");
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
            }
        }, "thread_A").start();

        new Thread(() -> {
            spinLock.lock();
            try {
                System.out.println("thread_B execute");
            } finally {
                spinLock.unlock();
            }
        }, "thread_B").start();
    }

    static class SpinLock {
        private AtomicReference<Thread> atomicReference = new AtomicReference<>();

        public void lock() {
            while (!atomicReference.compareAndSet(null, Thread.currentThread())) {

            }
        }

        public void unlock() {
            atomicReference.compareAndSet(Thread.currentThread(), null);
        }
    }
}
