package com.feiyue.javacore.base.keyword;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * final 并不保证变量的可见性
 * @author  feiyue
 * @date  2019/12/7
 */
public class FinalDemo {

    private static final Person p = new Person(1);
    private static Map<String, String> map = new HashMap<>();
    static ReadWriteLock lock = new ReentrantReadWriteLock();
    public static void main(String[] args) throws Exception{

        new Thread(() -> {
            //lock.writeLock().lock();
            try {
                TimeUnit.SECONDS.sleep(2);
                p.setAge(2);
                map.put("1", "11");
                System.out.println("code arrive son thread, age=" + p.getAge());
            } catch (Exception e) {e.printStackTrace();}
            /*finally {
                lock.writeLock().unlock();
            }*/
        }).start();

        /*Thread.sleep(3000);
        lock.readLock().lock();
        try {
            System.out.println(map.get("1"));
            // 主线程一直阻塞在此
            while (map.get("1") == null) {

            }
        } finally {
            lock.readLock().unlock();
        }*/

        // 主线程一直阻塞在此
        while (p.getAge() <=1) {
            // println 内部实现会对 PrintStream 加 synchronized 锁，线程加锁会清空工作内存的共享变量的值
            // System.out.println("haha");
        }
        System.out.println("code over, age=" + p.getAge());
    }
}
