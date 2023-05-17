package heimaSf.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BSTTree1 {

    BSTNode root;

    static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public Object get(int key) {
        return getRec(root, key);
    }

    public Object getRec(BSTNode bstNode, int key) {
        if (bstNode == null) {
            return null;
        }
        if (bstNode.key > key) {
            return getRec(bstNode.left, key);
        } else if (bstNode.key < key) {
            return getRec(bstNode.right, key);
        } else {
            return bstNode.value;
        }
    }

    public Object get1(int key) {
        BSTNode node = root;
        while (node != null) {
            if (node.key > key) {
                node = node.left;
            } else if (node.key < key) {
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

    public Object min(BSTNode bstNode) {
        BSTNode node = bstNode;
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
        return max(root);
    }

    public Object max(BSTNode bstNode) {
        BSTNode node = bstNode;
        while (node != null) {
            if (node.right == null) {
                return node.value;
            }
            node = node.right;
        }
        return null;
    }

    public void put(int key, Object value) {
        BSTNode node = root;
        BSTNode parent = null;
        while (node != null) {
            parent = node;
            if (key < node.key) {
                node = node.left;
            } else if (node.key < key) {
                node = node.right;
            } else {
                node.value = value;
                return;
            }
        }
        if (parent == null) {
            root = new BSTNode(key, value);
            return;
        }

        if (key < parent.key) {
            parent.left = new BSTNode(key, value);
        } else {
            parent.right = new BSTNode(key, value);
        }
    }

    public Object predecessor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromLeft = null;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (p.key < key) {
                ancestorFromLeft = p;
                p = p.right;
            } else {
                break;
            }
        }

        if (p == null) {
            return null;
        }

        if (p.left != null) {
            return max(p.left);
        }

        return ancestorFromLeft != null ? ancestorFromLeft.value: null;
    }

    public Object successor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromRight = null;
        while (p != null) {
            if (key < p.key) {
                ancestorFromRight = p;
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                break;
            }
        }

        if (p == null) {
            return null;
        }

        if (p.right != null) {
            return min(p.right);
        }

        return ancestorFromRight != null ? ancestorFromRight.value: null;
    }

    public Object delete(int key) {
        BSTNode p = root;
        BSTNode parent = null;
        while (p != null) {
            if (key < p.key) {
                parent = p;
                p = p.left;
            } else if (p.key < key) {
                parent = p;
                p = p.right;
            } else {
                break;
            }
        }

        if (p == null) {
            return null;
        }

        if (p.left == null) {
            shift(parent, p, p.right);
        } else if (p.right == null) {
            shift(parent, p, p.left);
        } else {
            BSTNode s = p.right;
            BSTNode sParent = p;
            while (s.left != null) {
                sParent = s;
                s = s.left;
            }
            if (sParent != p) {
                shift(sParent, s, s.right);
                s.right = p.right;
            }
            shift(parent, p, s);
            s.left = p.left;
        }

        return p.value;
    }

    public List<Object> less(int key) {
        List<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key < key) {
                    result.add(pop.key);
                } else {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }

    public List<Object> greater(int key) {
        List<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key > key) {
                    result.add(pop.key);
                }
                p = pop.right;
            }
        }
        return result;
    }

    public List<Object> between(int start, int end) {
        List<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key > start && pop.key < end) {
                    result.add(pop.key);
                } else if (pop.key > end) {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }

    private void shift(BSTNode parent, BSTNode deleted, BSTNode child) {
        if (parent == null) {
            root = child;
        } else if (deleted == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
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

        BSTTree1 bstTree1 = new BSTTree1();
        bstTree1.root = root;
        System.out.println(bstTree1.get1(1));
        System.out.println(bstTree1.get1(8));

        System.out.println(bstTree1.min());
        System.out.println(bstTree1.max());
        System.out.println(bstTree1.predecessor(4));
        System.out.println(bstTree1.successor(4));

        System.out.println(bstTree1.less(6));
        System.out.println(bstTree1.greater(6));
        System.out.println(bstTree1.between(1, 5));

    }
}
