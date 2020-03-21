package com.feiyue.concurrent;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {

    // 如果 i 不是 volatile，则主线程一致会阻塞在 while 循环中
    private static int i = 0;
    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            try {
                // 阻塞 3s 是为了防止子线程立刻执行完把刷新后的值刷入主内存，父线程已经拷贝一份 i=0 到工作内存
                while (i < 3) {
                }
                System.out.println("son thread read i first: " + i);
                TimeUnit.SECONDS.sleep(2);
                System.out.println("son thread read i first: " + i);
                i = 10;
                TimeUnit.SECONDS.sleep(2);
                System.out.println("son thread over, i=" + i);
            } catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        i = 2;
        TimeUnit.SECONDS.sleep(1);
        i = 3;
        TimeUnit.SECONDS.sleep(3);
        i = 4;
        while (i < 10) {

        }
        System.out.println("code over, i=" + i);
    }
}
