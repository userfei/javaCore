package com.feiyue.concurrent;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException{

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(2);
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(t1.isAlive());
        t1.join();
        System.out.println(3);
    }
}
