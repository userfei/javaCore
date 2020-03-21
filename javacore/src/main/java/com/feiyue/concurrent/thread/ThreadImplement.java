package com.feiyue.concurrent.thread;

/**
 * 线程的 Thread 的实现（不建议使用，1、不能继承其他类 2、尽量面向接口编程）
 * @author  feiyue
 * @date  2019/12/9
 */
public class ThreadImplement extends Thread {

    public static void main(String[] args) {
        ThreadImplement thread = new ThreadImplement();
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("new ThreadImplement");
    }
}
