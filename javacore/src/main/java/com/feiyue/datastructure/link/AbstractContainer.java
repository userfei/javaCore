package com.feiyue.datastructure.link;

/**
 * 容器抽象父类，求同存异
 * @author  feiyue
 * @date  2019/11/30
 */
public interface AbstractContainer<T> {
    int size();
    void insertFirst(T t);
    void insertLast(T t);
    void insertAt(int i, T t);
    T removeFirst();
    T removeLast();
    boolean exist(T t);

    /** 查询第几个结点中的元素 */
    T find(int i);

    /** 打印所有元素 */
    void printAll();
}
