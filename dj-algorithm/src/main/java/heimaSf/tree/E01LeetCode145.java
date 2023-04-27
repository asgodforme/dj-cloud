package heimaSf.tree;

import java.util.LinkedList;

/**
 * 前序遍历： 先访问该节点， 再访问左子树， 最后是右子树
 */
public class E01LeetCode145 {


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

        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode pop = null;
        TreeNode curr = root; // 代表当前节点
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr); // 通过栈记住回来的路
                colorPrintln("前: " + curr.val, 31);
                // 待处理左子树
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek(); // 栈顶元素
                // 右子树已经完成了处理
                if (peek.right == null) {
                    colorPrintln("中: " + peek.val, 36);
                    pop = stack.pop();
                    colorPrintln("后: " + pop.val, 34);
                    // 右子树完成了处理
                } else if (peek.right == pop) {
                    pop = stack.pop();
                    colorPrintln("后: " + pop.val, 34);
                    // 待处理右子树
                } else {
                    colorPrintln("中: " + peek.val, 36);
                    curr = peek.right;
                }
            }
        }

    }

    private static void colorPrintln(String origin, int color) {
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }


}
