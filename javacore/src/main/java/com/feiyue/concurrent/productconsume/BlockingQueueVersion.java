package com.feiyue.concurrent.productconsume;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过阻塞队列实现生产者和消费者问题
 * @author  feiyue
 * @date  2019/12/8
 */
public class BlockingQueueVersion {
    public static void main(String[] args) throws Exception{
        ResourceClass3 resource = new ResourceClass3(new ArrayBlockingQueue<>(3));
        new Thread(() -> {
            try {
                resource.product();
            } catch (Exception e) {e.printStackTrace();}
        }, "producer").start();

        new Thread(() -> {
            try {
                resource.consume();
            } catch (Exception e) {e.printStackTrace();}
        }, "consumer").start();

        TimeUnit.SECONDS.sleep(2);
        resource.stop();
    }
}

/**
 * 资源类： 1、线程同步操作的对象；2、操作资源类的方法归属资源类；3、假设资源只有0,1两种状态
 * @author  feiyue
 * @date  2019/12/8
 */
class ResourceClass3 {
    private AtomicInteger atomicInteger = new AtomicInteger();
    private volatile boolean flag = true;
    private BlockingQueue<Integer> queue;

    public ResourceClass3(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    protected void product() throws Exception{
        Integer data;
        boolean result;
        while (flag) {
            data = atomicInteger.incrementAndGet();
            result = queue.offer(data, 1, TimeUnit.SECONDS);
            if (result) {
                System.out.println("product: " + data + " success");
            } else {
                System.out.println("product: " + data + " failure");
            }
        }
    }

    protected void consume() throws Exception {
        while (flag) {
            Integer result = queue.poll(1, TimeUnit.SECONDS);
            if (result == null) {
                flag = false;
                System.out.println("consumer has no data to consume, finish");
                return;
            }
            System.out.println("consumer consume: " + result);
        }
    }

    protected void stop() {
        flag = false;
        System.out.println("stop the work");
    }
}