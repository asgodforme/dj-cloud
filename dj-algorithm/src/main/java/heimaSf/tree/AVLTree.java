package heimaSf.tree;


/**
 * AVL树
 *
 * 二叉搜索树在插入和删除时，节点可能失衡
 * 如果在插入和删除时通过旋转，始终让二叉搜索树保持平衡，称为自平衡的二叉搜索树
 * AVL是自平衡二叉搜索树的实现之一
 */
public class AVLTree {

    static class AVLNode {
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        // 如果一个节点的左右孩子，高度差超过1.则此节点失衡，才需要旋转
        int height = 1;

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right, int height) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = height;
        }
    }

    private int height(AVLNode node) {
        return node == null? 0: node.height;
    }

    private void updateHeight(AVLNode node) {
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
    }
}
