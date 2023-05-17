package heimaSf.tree;

public class BSTTree2<T extends Comparable<T>> {

    static class BSTNode<T> {
        T key;
        Object value;
        BSTNode<T> left;
        BSTNode<T> right;

        public BSTNode(T key) {
            this.key = key;
        }

        public BSTNode(T key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(T key, Object value, BSTNode<T> left, BSTNode<T> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    BSTNode<T> root;

    public Object get(T key) {
        BSTNode<T> node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    public Object min() {
//        return doMin(root);
        BSTNode node = root;
        while (node != null) {
            if (node.left == null) {
                return node.value;
            }
            node = node.left;
        }
        return null;
    }

    public Object doMin(BSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.value;
        }
        return doMin(node.left);
    }

    public Object max() {
        BSTNode node = root;
        while (node != null) {
            if (node.right == null) {
                return node.value;
            }
            node = node.right;
        }
        return null;
    }

    public void put(T key, Object value) {
        BSTNode<T> node = root;
        BSTNode<T> parent = null;
        while (node != null) {
            parent = node;
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                node.value = value;
                return;
            }
        }
        if (parent == null) {
            root = new BSTNode<>(key, value);
            return;
        }

        if (key.compareTo(parent.key) < 0) {
            parent.left = new BSTNode<>(key, value);
        } else if (key.compareTo(parent.key) > 0) {
            parent.right = new BSTNode<>(key, value);
        }
    }

    public static void main(String[] args) {
        BSTNode n1 = new BSTNode(1, "张无忌");
        BSTNode n3 = new BSTNode(3, "宋情书");
        BSTNode n2 = new BSTNode(2, "周芷若", n1, n3);

        BSTNode n5 = new BSTNode(5, "说不得");
        BSTNode n7 = new BSTNode(7, "阴历");
        BSTNode n6 = new BSTNode(6, "赵敏", n5, n7);

        BSTNode root = new BSTNode(4, "xiaozho", n2, n6);

        BSTTree2 bstTree = new BSTTree2();
        bstTree.root = root;
        System.out.println(bstTree.get(1));
        System.out.println(bstTree.get(8));
        System.out.println(bstTree.min());
        System.out.println(bstTree.max());


    }

}
