package com.feiyue.algorithm;

/**
 * 二叉查找树的实现
 * @author  feiyue
 * @date  2019/12/19
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
    private BinaryNode<T> root;
    private int size;

    BinarySearchTree() {
        size = 0;
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public boolean contains(T t, BinaryNode<T> node) {
        if (node == null) {
            return false;
        }
        int compareResult = t.compareTo(node.element);
        // 由于递归是有限最大为 lgN, 所以可以使用尾递归，当然也可以使用一个 while 循环替换
        if (compareResult < 0) {
            return contains(t, node.left);
        } else if (compareResult > 0) {
            return contains(t, node.right);
        } else {
            return true;
        }
    }

    /**
     * 使用递归方式
     */
    public T findMax(BinaryNode<T> node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.element;
        }
        return (T)findMax(node.right);
    }

    /**
     * 使用非递归方式
     */
    public T findMin(BinaryNode<T> node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node.element;
    }

    /**
     * 插入节点
     * @param node: 根节点为 node 的树进行插入
     * @return BinaryNode：返回这个根节点是为了递归操作
     */
    public BinaryNode<T> insert(T t, BinaryNode<T> node) {
        if (node == null) {
            node = new BinaryNode<T>(t, null, null);
            size++;
            return node;
        }
        int compareResult = t.compareTo(node.element);
        if (compareResult < 0) {
            node.left = insert(t, node.left);
        } else if (compareResult > 0) {
            node.right = insert(t, node.right);
        }
        return node;
    }

    /**
     * 移除操作，最为复杂(如果可以，可以进行懒惰删除（添加删除标记，实际不删除）)：
     *      1、如果是叶子叶节点，直接删除；
     *      2、只有一个子节点的节点：使用该子节点替代父节点
     *      3、有两个子节点的节点：使用右子树最小的节点代替该节点（左子树最大的节点代替）
     *
     * @param node: 根节点为 node 的树进行移除
     * @return BinaryNode：返回这个根节点是为了递归操作
     */
    public BinaryNode<T> remove(T t, BinaryNode<T> node) {
        if (node == null) {
            return node;
        }

        // 1、找到要删除的节点
        int compareResult = t.compareTo(node.element);
        if (compareResult < 0) {
            node.left = remove(t, node.left);
        } else if (compareResult > 0) {
            node.right = remove(t, node.right);
        } else if (node.right != null && node.left != null) {
            // 找到目标节点，且目标节点存在两个子节点
            T element = (T)findMin(node.right);
            node.element = element;
            node.right = remove(element, node.right);
            size--;
        } else {
            // 找到目标，且目标存在一个子节点或者没有子节点
            node = (node.right != null) ? node.right : node.left;
            size--;
        }
        return node;
    }

    /**
     * 嵌套内部类，表示树的每个节点
     * @author  feiyue
     * @date  2019/12/19
     */
    private static class BinaryNode<T> {
        T element;
        BinaryNode left;
        BinaryNode right;

        BinaryNode(T element) {
            this(element, null, null);
        }

        BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }
}
