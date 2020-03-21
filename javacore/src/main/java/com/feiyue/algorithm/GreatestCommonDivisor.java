package com.feiyue.algorithm;

import java.util.Scanner;

/**
 * 欧几里得算法求解最大公因子
 * @author  feiyue
 * @date  2019/12/1
 */
public class GreatestCommonDivisor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long m = scanner.nextLong();
        long n = scanner.nextLong();
        long r = gcd(m, n);
        System.out.printf("%s 和 %s 最大公因子：%s", m, n, r);
        System.out.println();

        long r2 = gcd2(m, n);
        System.out.printf("%s 和 %s 最大公因子：%s", m, n, r2);
    }

    /**
     * 利用余数
     * @author feiyue
     * @param  m:第一个整数
     * @param  n:第二个整数
     * @return 最大公因子
     */
    private static long gcd(long m, long n) {
        while (n != 0) {
            // 如果 m < n, 则会多经过一轮(m, n 值会交换)
            long k = m % n;
            m = n;
            n = k;
        }
        return m;
    }

    /**
     * 利用差值
     * @author feiyue
     * @param m:第一个整数
     * @param n:第二个整数
     * @return 最大公因子
     */
    private static long gcd2(long m, long n) {
        long k;
        if (m < n) {
            long temp = m;
            m = n;
            n = temp;
        }
        while (m % n != 0) {
            // 重新赋值 m, n
            k = m - n;
            m = n > k ? n : k;
            n = n + k - m;
        }
        return n;
    }
}
