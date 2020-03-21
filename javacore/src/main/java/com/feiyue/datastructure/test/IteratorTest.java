package com.feiyue.datastructure.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 测试 Iterator 接口的 remove 方法
 *      1、使用 iterator 的 remove 方法，需要先调用 next 方法获取元素，且二者是 1:1关系；
 *      2、Iterable 对象的增强 for 循环会被编译器优化，本质还是调用迭代器方法实现遍历
 *      3、在多线程环境下或集合作为静态全局变量的时候，只有迭代器调用自己修改集合的方法，迭代器才是合法的；
 *         或者说当一个线程在通过迭代器遍历集合，另外一个线程却在通过迭代器修改集合，
 *         这个时候会抛出 ConcurrentModificationException
 * @author  feiyue
 * @date  2019/12/2
 */
public class IteratorTest {

    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>(10);
        intList.add(1);
       /* intList.add(2);
        intList.add(3);
        intList.add(4);*/
        for (Integer i : intList) {
            System.out.println(i);
            intList.remove(i);
        }
        intList.remove(1);


        new Thread(new Runnable() {
            @Override
            public void run() {
                // 增强 for 循环，会被编译器优化成调用迭代的方法实现遍历
                /*for (Integer i : intList) {
                    System.out.println(i);
                }*/

                // 和下面 iterator 共存不会抛错
                for (int i = 0; i<intList.size(); i++) {
                    System.out.println(i);
                }
            }
        }).start();

        Iterator it = intList.iterator();
        while (it.hasNext()) {
            it.next();
            // it.remove 删除上一句 it.next 得到的元素, 一个 next 才能对应一个 remove
            it.remove();
        }
    }
}
