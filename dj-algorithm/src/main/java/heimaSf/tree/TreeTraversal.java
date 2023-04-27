package heimaSf.tree;

/**
 * 前序遍历： 先访问该节点， 再访问左子树， 最后是右子树
 */
public class TreeTraversal {


    /**
     *     1
     *    / \
     *   2   3
     *  /   / \
     * 4   5   6
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );
        System.out.print("前序遍历：");
        preOrder(root);
        System.out.print("中序遍历：");
        inOrder(root);
        System.out.print("后序遍历：");
        postOrder(root);

    }

    /**
     * 前序遍历： 先访问该节点， 再访问左子树， 最后是右子树
     * @param node
     */
    static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历： 先访问左子树， 再访问该节点， 最后是右子树
     * @param node
     */
    static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

    /**
     * 后序遍历： 先访问左子树， 再访问右子树， 最后是该节点
     * @param node
     */
    static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + " ");
    }

}
