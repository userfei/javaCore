package com.feiyue.datastructure.link;

/**
 * 双链表（含尾结点，含不带数据的头结点）的实现
 *      1、由于头部结点不存储数据，所以插入结点总是在 head 的后面
 * @author  feiyue
 * @date  2019/11/30
 */
public class DoubleLink<T> implements AbstractContainer<T> {

    private Node head;

    /** 带尾结点是为了方便对链表尾部进行操作 */
    private Node tail;
    private int size;

    private class Node<T> {
        private Node prev;
        private Node next;
        private T data;

        public Node() {
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    DoubleLink() {
        head = new Node();
        tail = head;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insertFirst(T t) {
       /* // 首节点为 head.next
        Node first = new Node();
        first.setData(t);
        first.setPrev(head);
        first.setNext(head.getNext());
        head.setNext(first);
        size++;

        // 插入前链表为空，插入后需要重置 tail 结点
        if (size == 1) {
            tail = first;
        }*/
       insertAt(0, t);
    }

    @Override
    public void insertLast(T t) {
        /*// 重置尾结点
        Node oldTail = tail;
        tail = new Node();
        tail.setData(t);
        tail.setNext(null);
        tail.setPrev(oldTail);

        // 将上个尾结点的 next 指向新的尾结点
        oldTail.setNext(tail);
        size++;*/
        insertAt(size - 1, t);

    }

    @Override
    public void insertAt(int i, T t) {
        if (i < 0 || i > size) {
            throw new RuntimeException("index out of bound");
        }

        // 获取待插入位置的上一个结点
        Node node = head;
        while (i-- > 0) {
            node = node.next;
        }
        Node nextNode = node.getNext();

        Node insertNode = new Node();
        insertNode.setData(t);
        insertNode.setPrev(node);
        insertNode.setNext(nextNode);

        node.setNext(insertNode);
        if (nextNode != null) {
            // 插入的不是尾结点
            nextNode.setPrev(insertNode);
        } else {
            // 插入的是尾结点
            tail = insertNode;
        }
        size++;
    }

    @Override
    public T removeFirst() {
        if (size < 1) {
            throw new RuntimeException("container is empty");
        }
        T data = (T)head.getNext().getData();
        Node secondNode = head.getNext().getNext();
        head.setNext(secondNode);
        if (size <= 1) {
            // 链表只有一个元素，移除后需要重置 tail
            tail = head;
        } else {
            secondNode.setPrev(head);
        }
        size--;
        return data;
    }

    @Override
    public T removeLast() {
        if (size < 1) {
            throw new RuntimeException("container is empty");
        }
        T data = (T)tail.getData();
        Node temp = tail.getPrev();
        temp.setNext(null);
        tail = temp;
        size--;
        return data;
    }

    @Override
    public boolean exist(T t) {
        // 链表初始化的时候会初始化 head
        Node temp = head.next;
        while (temp != null) {
            if (temp.getData().equals(t)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public T find(int i) {
        if (i < 0 || i > size) {
            throw new RuntimeException("index out of bound");
        }
        Node temp = head.next;
        while (i > 0) {
            temp = temp.next;
        }
        return (T)temp.getData();
    }

    @Override
    public void printAll() {
        Node n = head.next;
        while (n != null) {
            System.out.print(n.getData() + " ");
            n = n.next;
        }
    }
}
