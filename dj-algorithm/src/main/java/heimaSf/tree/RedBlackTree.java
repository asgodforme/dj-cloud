package heimaSf.tree;

import static heimaSf.tree.RedBlackTree.Color.BLACK;
import static heimaSf.tree.RedBlackTree.Color.RED;

public class RedBlackTree {

    enum Color {
        RED, BLACK;
    }

    private Node root;

    private static class Node {
        int key;
        Object value;
        Node left;
        Node right;
        Node parent;
        Color color = RED;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        Node uncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }

            if (parent.isLeftChild()) {
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }

        Node sibling() {
            if (parent == null) {
                return null;
            }
            if (parent.isLeftChild()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }
    }

    boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    boolean isBlack(Node node) {
        return !isRed(node);
    }

    /**
     * 右旋
     * @param pink
     */
    private void rightRotate(Node pink) {
        Node parent = pink.parent;
        Node yellow = pink.left;
        Node green = yellow.right;
        if (green != null) {
            green.parent = pink;
        }
        yellow.right = pink;
        yellow.parent = parent;
        pink.left = green;
        pink.parent = yellow;
        if (parent == null) {
            root = yellow;
        } else if (parent.left == pink) {
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }
    }

    private void leftRotate(Node pink) {
        Node parent = pink.parent;
        Node yellow = pink.right;
        Node green = yellow.left;
        if (green != null) {
            green.parent = parent;
        }
        yellow.left = pink;
        yellow.parent = parent;
        pink.right = green;
        pink.parent = yellow;
        if (parent == null) {
            root = yellow;
        } else if (parent.left == pink) {
            parent.left = yellow;
        } else {
            parent.right = yellow;
        }
    }

    public void put(int key, Object value) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            parent = p;
            if (p.key < key) {
                p = p.right;
            } else if (key < p.key) {
                p = p.left;
            } else {
                p.value = value;
                return;
            }
        }
        Node inserted = new Node(key, value);
        if (parent == null) {
            root = inserted;
        } else if (key < parent.key) {
            parent.left = inserted;
            inserted.parent = parent;
        } else {
            parent.right = inserted;
            inserted.parent = parent;
        }
        fixRedRed(inserted);
    }

    void fixRedRed(Node x) {
        if (x == root) {
            x.color = BLACK;
            return;
        }

        if (isBlack(x.parent)) {
            return;
        }

        Node parent = x.parent;
        Node uncle = x.uncle();
        Node grandparent = parent.parent;
        if (isRed(uncle)) {
            parent.color = BLACK;
            uncle.color = BLACK;
            grandparent.color = BLACK;
            fixRedRed(grandparent);
            return;
        }

        if (parent.isLeftChild() && x.isLeftChild()) {
            parent.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);
        } else if (parent.isLeftChild() && !x.isLeftChild()) {
            leftRotate(parent);
            x.color = BLACK;
            grandparent.color = RED;
        } else if (!parent.isLeftChild() && !x.isLeftChild()) {
            parent.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        } else {
            rightRotate(parent);
            x.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        }

    }

    public void remove(int key) {
        Node deleted = find(key);
        if (deleted == null) {
            return;
        }
        doRemove(deleted);

    }

    private void doRemove(Node deleted) {
        Node replaced = findReplaced(deleted);
        if (replaced == null) {

            return;
        }
        if (deleted.left == null || deleted.right == null) {

            return;
        }


    }

    Node find(int key) {
        Node p = root;
        while (p != null) {
            if (p.key < key) {
                p = p.right;
            } else if (key < p.key) {
                p = p.left;
            } else {
                return p;
            }
        }
        return null;
    }

    Node findReplaced(Node deleted) {
        if (deleted.left == null && deleted.right == null) {
            return null;
        }
        if (deleted.left == null) {
            return deleted.right;
        }
        if (deleted.right == null) {
            return deleted.left;
        }
        Node s = deleted.right;
        while (s.left != null) {
            s = s.left;
        }
        return s;
    }
}
