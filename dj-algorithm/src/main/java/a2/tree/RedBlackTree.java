package a2.tree;

/**
 * 用标准的二叉查找树和一些额外的信息替换3节点来表示2-3树
 *
 * @param <Key>
 * @param <Value>
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {

    private Node root; // 根节点
    private int n; // 树中元素的个数
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * 判断当前节点的父指向链接是否为红色
     * @param x
     * @return
     */
    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node hRight = h.rigth;
        Node lhRight = hRight.left;
        h.rigth = lhRight;
        hRight.left = h;
        hRight.color = h.color;
        h.color = RED;
        return hRight;
    }

    private class Node<Key, Value> {
        public Key key;
        public Value value;
        public Node left;
        public Node rigth;
        public boolean color;

        public Node(Key key, Value value, Node left, Node rigth, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.rigth = rigth;
            this.color = color;
        }
    }
}
