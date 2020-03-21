package com.feiyue.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

public class MsgQueueManager {

    /**
     * 消息总队列
     */
    private final BlockingQueue<Message> messageQueue;

    private MsgQueueManager() {
        messageQueue = new LinkedTransferQueue<Message>();
    }

    public void put(Message msg) {
        try {
            messageQueue.put(msg);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Message take() {
        try {
            return messageQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    static class DispatchMessageTask implements Runnable {

        @Override
        public void run() {
            BlockingQueue<Message> subQueue;
            for (;;) {
                // 如果没有数据，则阻塞这里
                // Message msg = MsgQueueFactory.getMessageQueue().take();
            }
        }
    }
}
