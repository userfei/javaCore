package com.feiyue.algorithm;

/**
 * 平衡二叉树
 * @author  feiyue
 * @date  2019/12/22
 */
public class AvlTree<T extends Comparable<? super T>> {

    // 可以允许左右子树高度差
    private static final int ALLOWED_IMBALANCE = 1;
    private AvlNode<T> root;

    public AvlTree() {
        root = null;
    }

    public AvlNode<T> getRoot() {
        return root;
    }

    public void insert(T data){
        root = insert(data, root);
    }

    public void remove(T data){
        root = remove(data, root);
    }

    public boolean isEmpty( ) {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if(isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }

    private void printTree(AvlNode<T> t) {
        if( t != null ) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    /**
     * 删除 t 为根节点的树中的某个节点(其值为value)
     * @param value: 待删除的节点的值
     * @param t: 删除节点所在树的根节点
     * @return 删除节点所在树的新根节点
     */
    private AvlNode<T> remove(T value, AvlNode<T> t) {
        if (t == null) {
            return t;
        }
        int compareResult = value.compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(value, t.left);
        } else if (compareResult > 0) {
            t.right = remove(value, t.right);
        } else if(t.left != null && t.right != null) {
            // 使用右子树最小节点替换当前根节点
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return balance(t);
    }

    private AvlNode<T> findMin(AvlNode<T> t)
    {
        if( t == null ) {
            return t;
        }
        while(t.left != null) {
            t = t.left;
        }
        return t;
    }

    /**
     * 向子树中插入新节点(其值为 value)
     * @param value: 待插入节点的值
     * @param t: 插入节点所在树的根节点
     * @return 返回插入节点所在树的新根节点
     */
    private AvlNode<T> insert(T value, AvlNode<T> t) {
        if (t == null) {
            return new AvlNode<T>(value, null, null);
        }
        int compareResult = value.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(value, t.left);
        } else if (compareResult > 0) {
            t.right = insert(value, t.right);
        }
        return balance(t);
    }

    /**
     * 平衡 t 的左右子树
     */
    private AvlNode<T> balance(AvlNode<T> t) {
        if (t == null) {
            return t;
        }
        if(height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
            if (height(t.left.left) >= height(t.left.right)) {
                // left-left 情况
                t = rotateWithLeftChild(t);
            } else {
                // left-right 情况
                t = doubleWithLeftChild(t);
            }
        } else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
            if (height(t.right.right) >= height(t.right.left)) {
                // right-right 情况
                t = rotateWithRightChild(t);
            } else {
                // right-left 情况
                t = doubleWithRightChild(t);
            }
        }

        // 根节点的高度 = 左右子树高度较大者 + 1;
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    /**
     * 使用双旋转处理，左儿子右子树插入的问题
     *      1、先进行一次左旋转，将 left-right 变成 left-left
     *      2、然后进行一次右旋转
     */
    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * 使用右旋处理左儿子左子树插入的问题
     *      1、定义一个变量存储待平衡节点的左子节点 AvlNode t = root.left;
     *      2、待平衡的节点的左子节点的右子节点变换成待平衡节点的左子节点 root.left = root.left.right;
     *      3、待平衡的节点变换成其左子节点的右子节点 root.left.right = root;
     */
    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> k1) {

        // 使用一次右旋转(使得right-left变成right-right)，再使用一次左旋转
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> k1) {
        AvlNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(k1.height, height(k2.right)) + 1;
        return k2;
    }

    /**
     * 节点的高度
     */
    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }

    /**
     * 内部嵌套类：平衡二叉树节点
     * @author  feiyue
     * @date  2019/12/22
     */
    private static class AvlNode<T> {

        T element;
        AvlNode<T> left;
        AvlNode<T> right;
        int height; // 节点高度，左右子树高度的较大值

        AvlNode(T element) {
            this(element, null, null);
        }

        AvlNode(T element, AvlNode<T> lt, AvlNode<T> rt) {
            this.element = element;
            left = lt;
            right = rt;
            height = 0;
        }
    }
}
