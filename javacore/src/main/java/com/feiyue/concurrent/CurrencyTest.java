package com.feiyue.concurrent;

public class CurrencyTest {
    private static final long count = 10000L;

    public static void main(String[] args) throws InterruptedException {
        currency();
        serial();
    }

    // 线程并发执行
    private static void currency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                int a = 0;
                for(long i=0; i<count; i++) {
                    a +=5;
                }
            }
        });
        thread.start();

        int b = 0;
        for(long i = 0; i<count; i++) {
            b--;
        }
        thread.join();  // 当前线程等待(wait) thread 线程执行结束(isAlive 为 false)
        long time = System.currentTimeMillis() - start;
        System.out.println("currency:" + time + "ms, b="+b);
    }

    // 线程串行执行
    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for(long i=0; i<count; i++) {
            a += 5;
        }

        int b = 0;
        for(long i=0; i<count; i++){
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms, b="+b+", a="+a);
    }
}
