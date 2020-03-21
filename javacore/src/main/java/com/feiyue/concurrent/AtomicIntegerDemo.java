package com.feiyue.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子更新整型
 * @author  feiyue
 * @date  2019/9/15
 */
public class AtomicIntegerDemo {
    static int[] value = new int[]{1, 2};

    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {

        // addAndGet(i, delta) 原子方式将数组索引为 i 的元素的值加上 delta
        ai.addAndGet(0, 3);

        // compareAndSet(i, expect, update)
        ai.compareAndSet(0, 3, 4);

        AtomicInteger atomicInt = new AtomicInteger(1);

        // addAndGet(delta) 原子方式将输入的值(delta) 和实例中的值(atomicInt)相加，返回结果，结果会赋值给实例
        atomicInt.addAndGet(1);
        System.out.println(atomicInt.get());

        // compareAndSet(expect, update) 如果实例的值等于 expect 值，则更新实例值为 update 值
        atomicInt.compareAndSet(1, 4);
        System.out.println(atomicInt.get());

        // getAndIncrement() 原子方式给实例值加1, 方法返回自增前的值
        atomicInt.getAndIncrement();
        System.out.println(atomicInt.get());

        // lazySet(newValue) 稍做延迟设置实例值为 newValue
        atomicInt.lazySet(1);
        System.out.println(atomicInt.get());

        // getAndSet(newValue) 原子方式设置实例值为 newValue 值，并返回旧值
        atomicInt.getAndSet(1);
        System.out.println(atomicInt.get());

    }
}
