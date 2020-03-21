package com.feiyue.datastructure.link;

/**
 * 单链表实现（带数据的头结点）
 * @author  feiyue
 * @date  2019/11/30
 */
public class SingleLink<T> implements AbstractContainer<T> {

    private Node head;
    private int size;
    private class Node<T> {
        T data;
        Node next;

        public Node() {
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insertFirst(T t) {
        Node oldHead = head;
        head = new Node(t, oldHead);
        size++;
    }

    @Override
    public void insertLast(T t) {
        Node last = new Node(t, null);
        Node n = head;
        while(n.getNext() != null) {
            n = n.next;
        }
        n.setNext(last);
        size++;
    }

    /**
     * 在链表第 i 个位置插入元素 t
     * @param i: 待插入的位置
     * @param t: 待插入的元素
     * @return
     */
    @Override
    public void insertAt(int i, T t) {
        if (i < 0 || i > size) {
            throw new RuntimeException("index out of the bound");
        }
        if (i == 0) {
            insertFirst(t);
        } else {
            Node n = head;
            while (--i > 0) {
                n = n.next;
            }
            Node newNode = new Node();
            newNode.setData(t);
            newNode.setNext(n.getNext());
            n.setNext(newNode);
            size++;
        }
    }

    @Override
    public T removeFirst() {
        if (size <= 0) {
            throw new RuntimeException("element not exist");
        }
        T t = (T)head.getData();
        head = head.next;
        size--;
        return t;
    }

    @Override
    public T removeLast() {
        if (size <= 0) {
            throw new RuntimeException("element not exist");
        }
        if (size == 1) {
            removeFirst();
        } else {
            int i = size;
            Node node = head;
            // 获取链表倒数第二个元素所在的结点
            while (--i > 1) {
                node = node.next;
            }
            T t = (T)node.getData();
            node.setNext(null);
            size--;
            return t;
        }
        return null;
    }

    @Override
    public T find(int i) {
        if (i < 0 || i >= size) {
            throw new RuntimeException("index out of the bound");
        }
        Node node = head;
        while (i-- > 0) {
            node = node.next;
        }
        return (T)node.getData();
    }

    @Override
    public boolean exist(T t) {
        Node node = head;
        while (node != null) {
            if (node.getData().equals(t)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public void printAll() {
        Node n = head;
        while(n != null) {
            System.out.print(n.getData() + " ");
            n = n.next;
        }
    }
}
