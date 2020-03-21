package com.feiyue.concurrent.databaseConnectionPool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 数据库连接池
 * @author  feiyue
 * @date  2019/10/7
 */
public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();

    // 初始化连接池
    public ConnectionPool(int initialSize) {
        if(initialSize > 0) {
            for (int i = 0; i<initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    // 释放连接
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                // 连接释放之后需要通知其他消费者有连接归还了
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    // 获取连接
    public Connection fetchConnection(long time) throws InterruptedException {
        synchronized (pool) {
            if (time <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + time;
                long remaining = time;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    return pool.removeFirst();
                }
                return result;
            }
        }
    }
}
