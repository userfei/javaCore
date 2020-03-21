package com.feiyue.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Priority {

    private static volatile boolean nonStart = true;
    private static volatile boolean nonEnd = true;
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    public static void main(String[] args) throws Exception{
        List<Job> jobs = new ArrayList<Job>();

        new ScheduledThreadPoolExecutor(1);

        // 开启 10 个线程，5个优先级为1，5个优先级为10
        for(int i=0; i<10; i++){
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority, ""+i);
            jobs.add(job);
            Thread thread = new Thread(job, "thread:"+i);
            thread.setPriority(priority);
            thread.start();
        }

        // 新开的10个线程，在10秒钟内竞争 CPU 资源
        nonStart = false;
        TimeUnit.SECONDS.sleep(10);
        nonEnd = false;

        for(Job job : jobs){
            System.out.println("Job Priority : " + job.priority + ", count: " + job.jobCount);
        }
    }

    static class Job implements Runnable{

        private int priority;
        private long jobCount;
        private String name;

        public Job(int priority, String name) {
            this.priority = priority;
            this.name = name;
        }

        @Override
        public void run() {
            while(nonStart){
                // yield 译为线程让步。顾名思义，就是说当一个线程使用了这个方法之后，它就会把自己CPU执行的时间让掉，
                // 让自己或者其它的线程运行，注意是让自己或者其他线程运行，并不是单纯的让给其他线程。
                Thread.yield();
                System.out.println(name);
            }

            while (nonEnd){
                Thread.yield();
                jobCount++;
            }
        }
    }
}
