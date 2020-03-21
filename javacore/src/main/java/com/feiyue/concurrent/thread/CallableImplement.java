package com.feiyue.concurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 线程的 Callable 的实现
 *    1、FutureTask<V> --实现--> RunnableFuture<V> --继承--> Runnable, Future<V>
 *    2、Callable 的出现是为了在多线程情况下获取线程的返回值，从而判断线程执行情况
 * @author  feiyue
 * @date  2019/12/9
 */
public class CallableImplement implements Callable<String> {

    public static void main(String[] args) throws Exception {

        // FutureTask 内部组合一个 Callable 对象
        FutureTask<String> future = new FutureTask<>(new CallableImplement());
        Thread thread = new Thread(future, "threadName");
        Thread thread2 = new Thread(future, "threadName2");
        thread.start();
        thread2.start();

        // futureTask.get() 阻塞获取结果，如无必要尽量放在方法最后避免阻塞其他代码的执行
        System.out.println(future.get());
    }

    @Override
    public String call() throws Exception {
        System.out.println("Callable implement");
        return "i am coming";
    }
}
