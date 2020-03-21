package com.feiyue.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁的重入演示
 * @author  feiyue
 * @date  2019/12/7
 */
public class ReentrantLockDemo {

    static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.call();
        }, "t1").start();

        new Thread(() -> {
            phone.transmit();
        }, "t2").start();

        lock.lock();
        // 主线程再次获取 lock
        lock.lock();
        try {
            System.out.println("test reentrant lock");
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }
}

class Phone {

    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + " invoke call method");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // transmit 和 call 都是实例方法，所以二者对应的锁均是实例对象锁
        transmit();
    }

    public synchronized void transmit() {
        System.out.println(Thread.currentThread().getName() + " transmit voice content by call");
    }
}
