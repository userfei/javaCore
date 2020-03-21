package com.feiyue.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁实现缓存
 * @author  feiyue
 * @date  2019/12/7
 */
public class Cache {

    static Map<String, Object> map = new HashMap<>();
    static ReentrantReadWriteLock rw1 = new ReentrantReadWriteLock();
    static Lock r = rw1.readLock();     // 获取读锁
    static Lock w = rw1.writeLock();

    // 获取 key 对应的 value
    public static final Object get(String key) {
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    // 设置 key 对应的 value
    public static final Object put(String key, Object value) {
        w.lock();
        try {
            return map.put(key, value);
        } finally {
            w.unlock();
        }
    }
}
