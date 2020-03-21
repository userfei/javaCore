package com.feiyue.algorithm;

import java.util.Scanner;

/**
 * 幂运算 x^n
 * @author  feiyue
 * @date  2019/12/1
 */
public class Exponentiation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        long r = pow(m, n);
        System.out.printf("%d 的 %d 次幂：%d", m, n, r);
    }

    // 使用递归方式，若 n 为偶数则拆分成 n/2 和 n/2，若为奇数则拆分成 (n-1)/2, (n-1)/2 和 1
    private static long pow(int m, int n) {
        if (n == 0) {
            return 1;
        }
        if (n % 2 == 0) {
            // pow(m, n/2) * pow(m, n/2) 会重复计算 pow(m, n/2)，影响效率
            return pow(m * m, n / 2);
        } else {
            return pow(m * m, n / 2) * m;
        }
    }
}
