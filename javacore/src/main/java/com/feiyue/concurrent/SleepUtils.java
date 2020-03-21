package com.feiyue.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 线程睡眠
 * @author  feiyue
 * @date  2019/9/7
 */
public class SleepUtils {

    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e){}
    }
}
