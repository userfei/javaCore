package com.feiyue.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 的使用
 * @author  feiyue
 * @date  2019/10/7
 */
public class ThreadLocalProfiler {
    private static final ThreadLocal<Long> TIME_THREAD_LOCAL = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static void main(String[] args) throws Exception {
        ThreadLocalProfiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + ThreadLocalProfiler.end() + " mills");
    }

    public static final void begin() {
        TIME_THREAD_LOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREAD_LOCAL.get();
    }
}
