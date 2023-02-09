package a2.tree;

/**
 * 用标准的二叉查找树和一些额外的信息替换3节点来表示2-3树
 *
 * @param <Key>
 * @param <Value>
 */
public class RedBlackTree<Key, Value> {

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
