package com.feiyue.algorithm;

public class AvlTreeDemo {

    public static void main(String[] args) {
        AvlTree<Integer> tree = new AvlTree();
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(4);
        tree.insert(5);
        tree.insert(9);
        tree.insert(8);
        tree.printTree();

    }
}