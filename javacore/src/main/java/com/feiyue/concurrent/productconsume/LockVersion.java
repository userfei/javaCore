package com.feiyue.concurrent.productconsume;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过Lock锁实现生产者和消费者问题
 * @author  feiyue
 * @date  2019/12/8
 */
public class LockVersion {
    public static void main(String[] args) {
        ResourceClass2 resource = new ResourceClass2();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.product();
            }
        }, "producer").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.consume();
            }
        }, "consumer").start();
    }
}

/**
 * 资源类： 1、线程同步操作的对象；2、操作资源类的方法归属资源类；3、假设资源只有0,1两种状态
 * @author  feiyue
 * @date  2019/12/8
 */
class ResourceClass2 {
    private int i = 0;
    private Lock lock = new ReentrantLock();

    /**
     *  这里之所以使用两个 condition 是为了提高效率:
     *      1、多个线程同时等待 condition 的时候，如果有线程调用 condition.notifyAll 会无区别的尝试唤醒
     *      也就是说，当存在多个生产者，生产者唤醒等待线程可能还会唤醒生产者（稍后又进入睡眠），所以使用多种
     *      condition 可以针对性的唤醒
     */
    private Condition productCondition = lock.newCondition();
    private Condition consumerCondition = lock.newCondition();

    protected void product() {
        lock.lock();
        try {
            // 防止虚假唤醒，使用 while 而不是 if
            while (i != 0) {
                productCondition.await();
            }
            i++;
            System.out.println("producer --> " + i);
            consumerCondition.signalAll();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    protected void consume() {
        lock.lock();
        try {
            // 防止虚假唤醒，使用 while 而不是 if
            while (i != 1) {
                consumerCondition.await();
            }
            i--;
            System.out.println("consumer --> " + i);
            productCondition.signalAll();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }
}
