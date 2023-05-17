package heimaSf.tree;

import java.util.LinkedList;

public class E08ExpressionTree {

    static class TreeNode {
        public String val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(String val) {
            this.val = val;
        }

        public TreeNode(String val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return val;
        }
    }

    static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + " ");
    }

    public static TreeNode constructExpressionTree(String[] tokens) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        for (String token: tokens) {
            switch (token) {
                case "+":
                case "-":
                case "*":
                case "/":
                    TreeNode right = stack.pop();
                    TreeNode left = stack.pop();
                    TreeNode parent = new TreeNode(token);
                    parent.left = left;
                    parent.right = right;
                    stack.push(parent);
                    break;
                default:
                    stack.push(new TreeNode(token));
                    break;
            }

        }
        return stack.peek();
    }

    public static void main(String[] args) {
        String[] tokens= {"2", "1", "-", "3", "*"};
        TreeNode treeNode = constructExpressionTree(tokens);
        postOrder(treeNode);
    }
}
