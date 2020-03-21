package com.feiyue.concurrent;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 计算银行流水
 * @author  feiyue
 * @date  2019/10/19
 */
public class BankWaterService implements Runnable {
    /**
     * 创建 4 个屏障，处理完之后执行当前类的 run 方法
     */
    private CyclicBarrier c = new CyclicBarrier(4, this);

    private Executor executor = Executors.newFixedThreadPool(4);

    /**
     * 保存每个 sheet 计算池的银行流水结果
     */
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        BankWaterService bankWaterCount = new BankWaterService();
        bankWaterCount.count();
    }

    private void count() {
        for (int i = 0; i<4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    // 省略计算银行流水代码
                    sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                    try {
                        c.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        int result = 0;
        // 汇总每个 sheet 计算出的结果
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }

        // 输出结果
        sheetBankWaterCount.put("result", result);
        System.out.println(result);
    }
}
