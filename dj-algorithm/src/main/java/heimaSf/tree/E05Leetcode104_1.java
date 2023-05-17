package heimaSf.tree;

import jdk.nashorn.internal.ir.LabelNode;
import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class E05Leetcode104_1 {

    public static int maxDepth1(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int i = maxDepth(node.left);
        int j = maxDepth(node.right);
        return Integer.max(i, j) + 1;
    }

    public static int minDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int i = minDepth(node.left);
        int j = minDepth(node.right);
        if (i == 0) {
            return j + 1;
        }
        if (j == 0) {
            return i + 1;
        }
        return Integer.min(i, j) + 1;
    }

    public static int maxDepth2(TreeNode node) {
        TreeNode curr = node;
        TreeNode pop = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int max = 0;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                if (stack.size() > max) {
                    max = stack.size();
                }
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                } else {
                    curr = peek.right;
                }
            }

        }
        return max;
    }

    public static int maxDepth(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int max = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            max++;
        }
        return max;
    }

    public static int minDepth1(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }

        }
        return depth;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(new TreeNode(2), 1, new TreeNode(3));
        System.out.println(maxDepth(t1));
        TreeNode t2 = new TreeNode(new TreeNode(2), 1, null);
        System.out.println(minDepth(t2));
    }
}
