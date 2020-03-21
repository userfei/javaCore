package com.feiyue.datastructure.link;

/**
 * 单链表应用
 * @author  feiyue
 * @date  2019/11/30
 */
public class SingleLinkDemo {

    public static void main(String[] args) {
        AbstractContainer<Integer> intContainer = new SingleLink<>();
        intContainer.insertFirst(1);
        intContainer.insertLast(2);
        intContainer.insertLast(3);
        intContainer.insertFirst(4);
        intContainer.insertAt(2, 5);
        intContainer.removeLast();
        System.out.println(intContainer.exist(3));
        System.out.println(intContainer.size());
        System.out.println(intContainer.find(2));
        intContainer.printAll();
    }
}
