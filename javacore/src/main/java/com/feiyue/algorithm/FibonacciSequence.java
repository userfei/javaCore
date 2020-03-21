package com.feiyue.algorithm;

/**
 * 斐波那契数列
 * @author  feiyue
 * @date  2019/12/1
 */
public class FibonacciSequence {

    public static void main(String[] args) {
        // 斐波那契数列，时间换取空间的算法
        long begin = System.currentTimeMillis();
        long result = fib(44);
        System.out.printf("get the result:%s, cost time:%sms", result, System.currentTimeMillis() - begin);
        System.out.println();

        // 斐波那契数列，空间换取时间的算法
        long start = System.currentTimeMillis();
        long answer = fib2(44);
        System.out.printf("get the answer:%s, cost time:%sms", answer, System.currentTimeMillis() - start);
    }

    public static long fib(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static long fib2(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] digitalArray = new int[n];
        digitalArray[0] = 1;
        digitalArray[1] = 1;
        for (int j = 2; j < n; j++) {
            digitalArray[j] = digitalArray[j-1] + digitalArray[j-2];
        }
        return digitalArray[n - 1] + digitalArray[n - 2];
    }
}
