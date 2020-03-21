package com.feiyue.concurrent.databaseConnectionPool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟客户端操作数据库连接池
 * @author  feiyue
 * @date  2019/10/7
 */
public class ConnectionPoolTest {

    static ConnectionPool pool = new ConnectionPool(10);

    // 保证所有的 ConnectionRunner 能够同时开始
    static CountDownLatch start = new CountDownLatch(1);

    // main 线程将会等待所有 ConnectionRunner 结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        int threadCount = 10;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i<threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + got);
        System.out.println("not got connection: " + notGot);
    }

    private static class ConnectionRunner implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (Exception e) {}

            while (count > 0) {
                try{
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception e){
                }finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
