package com.feiyue.concurrent.productconsume;

/**
 * 通过synchronize锁实现生产者和消费者问题
 * @author  feiyue
 * @date  2019/12/8
 */
public class SynchronizedVersion {
    public static void main(String[] args) {
        ResourceClass resource = new ResourceClass();
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
class ResourceClass {
    private int i = 0;

    protected synchronized void product() {
        // 防止虚假唤醒，使用 while 而不是 if
        while (i != 0) {
            try {
                wait();
            } catch (Exception e){}
        }
        i++;
        System.out.println("producer --> " + i);
        notifyAll();
    }

    protected synchronized void consume() {
        // 防止虚假唤醒，使用 while 而不是 if
        while (i != 1) {
            try {
                wait();
            } catch (Exception e){}
        }
        i--;
        System.out.println("consumer --> " + i);
        notifyAll();
    }

    public int getI() {
        return i;
    }
}


