package com.feiyue.concurrent;

import java.util.concurrent.*;

public class CountTask extends RecursiveTask<Integer> {

    // 阈值
    private static final int THRESHOLD = 2;

    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        // 如果任务小直接计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = sum; i<=end; i++) {
                sum +=i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (end - start) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // 等待子任务执行完，并得到结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool= new ForkJoinPool();

        // 生成一个计算任务，负责计算 1+2+3+4
        CountTask task = new CountTask(1, 4);

        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e){
        } catch (ExecutionException e){
        }
    }
}
