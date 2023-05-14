package heimaSf.tree.queue;

public class E01leetcode102 {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        TreeNode t2 = new TreeNode(3, new TreeNode(6), new TreeNode(7));
        TreeNode t = new TreeNode(1, t1, t2);


        LinkedListQueue<TreeNode> queue = new LinkedListQueue<>();
        queue.offer(t);
        int c1 = 1;
        while (!queue.isEmpty()) {
            int c2 = 0;
            for (int i = 0; i < c1; i++) {
                TreeNode poll = queue.poll();
                System.out.print(poll + " ");
                if (poll.left != null) {
                    c2++;
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    c2++;
                    queue.offer(poll.right);
                }
            }
            System.out.println();
            c1 = c2;
        }

    }
}
