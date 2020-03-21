package com.feiyue.datastructure.link;

/**
 * 双链表应用
 * @author  feiyue
 * @date  2019/11/30
 */
public class DoubleLinkDemo {

    public static void main(String[] args) {
        AbstractContainer<Integer> container = new DoubleLink<>();
        /*container.insertFirst(2);
        container.insertFirst(1);
        container.insertLast(4);*/
        container.insertAt(0, 3);
        /*container.removeLast();
        container.removeFirst();*/
        container.printAll();
    }
}
