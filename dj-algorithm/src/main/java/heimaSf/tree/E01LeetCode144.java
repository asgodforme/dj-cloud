package heimaSf.tree;

import java.util.LinkedList;

/**
 * 前序遍历： 先访问该节点， 再访问左子树， 最后是右子树
 */
public class E01LeetCode144 {


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


        TreeNode curr = root; // 代表当前节点
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                colorPrintln("去: " + curr.val, 31);
                stack.push(curr); // 通过栈记住回来的路
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();
                colorPrintln("回: " + pop.val, 34);
                curr = pop.right;
            }
        }

    }

    private static void colorPrintln(String origin, int color) {
        System.out.printf("\033[%dm%s\033[0m%n", color, origin);
    }


}
