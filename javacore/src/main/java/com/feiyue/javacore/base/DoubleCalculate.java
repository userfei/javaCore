package com.feiyue.javacore.base;

public class DoubleCalculate {

    public static void main(String[] args){

        DoubleCalculate calculate = new DoubleCalculate();

        // 除以 0
        System.out.println(calculate.divide(2.0, 0));  // Infinity

        // 浮点运算
        System.out.println(calculate.sub(2.0, 1.1));  // 0.8999999999999999 有误差
        System.out.println(calculate.sub(1.0, 0.1));  // 0.9 - 有误差, 有进位

    }

    // 处理器默认的浮点运算
    private Double divide(double a, double b){
        return a/0.3*b;
    }

    // strictfp 指定严格浮点运算
    private strictfp Double divide2(double a, double b){
        return a/0.3*b;
    }

    private Double sub(double a, double b){
        return a-b;
    }
}
