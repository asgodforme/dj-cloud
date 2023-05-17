package heimaSf.tree;

import java.util.Arrays;

public class E09Leetcode105 {

    public TreeNode buildTree1(int[] preOrder, int[] inOrder) {
        if (preOrder.length == 0) {
            return null;
        }
        int rootValue = preOrder[0];
        TreeNode root = new TreeNode(rootValue);
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootValue) {
                int[] inleft = Arrays.copyOfRange(inOrder, 0, i);
                int[] inrigth = Arrays.copyOfRange(inOrder, i + 1, inOrder.length);

                int[] preleft = Arrays.copyOfRange(preOrder, 1, i + 1);
                int[] preright = Arrays.copyOfRange(preOrder, i + 1, preOrder.length);

                root.left = buildTree1(preleft, inleft);
                root.right = buildTree1(preright, inrigth);

                break;
            }
        }
        return root;
    }

    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
        if (inOrder.length == 0) {
            return null;
        }
        int rootValue = postOrder[postOrder.length - 1];
        TreeNode root = new TreeNode(rootValue);
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootValue) {
                int[] inleft = Arrays.copyOfRange(inOrder, 0, i);
                int[] inrigth = Arrays.copyOfRange(inOrder, i + 1, inOrder.length);

                int[] postleft = Arrays.copyOfRange(postOrder, 0, i);
                int[] postright = Arrays.copyOfRange(postOrder, i, postOrder.length - 1);

                root.left = buildTree(inleft, postleft);
                root.right = buildTree(inrigth, postright);

                break;
            }
        }
        return root;
    }
}
