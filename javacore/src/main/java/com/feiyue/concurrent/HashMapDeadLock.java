package com.feiyue.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 多线程环境下 HashMap 死循环案例
 * @author  feiyue
 * @date  2019/10/11
 */
public class HashMapDeadLock {

    public static void main(String[] args) throws Exception{
        deadLock();
    }

    public static void deadLock() throws InterruptedException{
        final Map<String, String> map = new HashMap<>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<1000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        t.join();
    }
}
