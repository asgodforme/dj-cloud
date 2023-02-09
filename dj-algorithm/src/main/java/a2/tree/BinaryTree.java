package a2.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree<Key extends Comparable<Key>, Value> {
    private Node root;
    private int n;

    public int size() {
        return n;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    //向指定的树x中添加key-value,并返回添加元素后新的树
    private Node put(Node root, Key key, Value value) {
        if (root == null) {
            n++;
            return new Node(key, value, null, null);
        }
        int cmp = key.compareTo(root.key);
        if (cmp > 0) {
            //新结点的key大于当前结点的key，继续找当前结点的右子结点
            root.right = put(root.right, key, value);
        } else if (cmp < 0) {
            //新结点的key小于当前结点的key，继续找当前结点的左子结点
            root.left = put(root.left, key, value);
        } else {
            //新结点的key等于当前结点的key，把当前结点的value进行替换
            root.value = value;
        }
        return root;
    }

    //查询树中指定key对应的value
    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            //如果要查询的key大于当前结点的key，则继续找当前结点的右子结点；
            return get(x.right, key);
        } else if (cmp < 0) {
            //如果要查询的key小于当前结点的key，则继续找当前结点的左子结点；
            return get(x.left, key);
        } else {
            return x.value;
        }
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    public Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            x.right = delete(x.right, key);
        } else if (cmp < 0) {
            x.left = delete(x.left, key);
        } else {
            //新结点的key等于当前结点的key,当前x就是要删除的结点
            //1.如果当前结点的右子树不存在，则直接返回当前结点的左子结点
            if (x.right == null) {
                n--;
                return x.left;
            }
            //2.如果当前结点的左子树不存在，则直接返回当前结点的右子结点
            if (x.left == null) {
                n--;
                return x.right;
            }
            //3.当前结点的左右子树都存在
            //3.1找到右子树中最小的结点
            Node minNode = x.right;
            while (minNode.left != null) {
                minNode = minNode.left;
            }

            //3.2删除右子树中最小的结点
            Node node = x.right;
            while (node.left != null) {
                if (node.left.left == null) {
                    node.left = null;
                } else {
                    node = node.left;
                }
            }
            //3.3让被删除结点的左子树称为最小结点minNode的左子树，让被删除结点的右子树称为最小结点minNode的右子树
            minNode.left = x.left;
            minNode.right = x.right;

            //3.4让被删除结点的父节点指向最小结点minNode
            x = minNode;
            n--;
        }
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    public Node min(Node x) {
        if (x.left != null) {
            return min(x.left);
        } else {
            return x;
        }
    }

    public Key max() {
        return max(root).key;
    }

    public Node max(Node x) {
        if (x.right != null) {
            return max(x.right);
        } else {
            return x;
        }
    }

    public List<Key> preErgodic() {
        List<Key> keys = new ArrayList<>();
        preErgodic(root, keys);
        return keys;
    }

    private void preErgodic(Node root, List<Key> keys) {
        if (root == null) {
            return;
        }
        keys.add(root.key);
        if (root.left != null) {
            preErgodic(root.left, keys);
        }
        if (root.right != null) {
            preErgodic(root.right, keys);
        }
    }

    public List<Key> midErgodic() {
        List<Key> keys = new ArrayList<>();
        midErgodic(root, keys);
        return keys;
    }

    private void midErgodic(Node root, List<Key> keys) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            midErgodic(root.left, keys);
        }
        keys.add(root.key);
        if (root.right != null) {
            midErgodic(root.right, keys);
        }
    }

    public List<Key> afterErgodic() {
        List<Key> keys = new ArrayList<>();
        afterErgodic(root, keys);
        return keys;
    }

    private void afterErgodic(Node root, List<Key> keys) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            afterErgodic(root.left, keys);
        }
        if (root.right != null) {
            afterErgodic(root.right, keys);
        }
        keys.add(root.key);
    }

    public Queue<Key> layerErgodic() {
        Queue<Key> keys = new LinkedList<>();
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node x = nodes.poll();
            keys.add(x.key);
            if (x.left != null) {
                nodes.add(x.left);
            }
            if (x.right != null) {
                nodes.add(x.right);
            }
        }
        return keys;
    }

    public int maxDepth() {
        return maxDepth(root);
    }

    private int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        int maxL = 0;
        int maxR = 0;
        if (root.left != null) {
            maxL = maxDepth(root.left);
        }
        if (root.right != null) {
            maxR = maxDepth(root.right);
        }
        max = maxL > maxR ? maxL + 1: maxR + 1;
        return max;
    }


    private class Node {
        public Key key;
        public Value value;
        public Node left;
        public Node right;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws Exception {
        /*BinaryTree<Integer, String> bt = new BinaryTree<>();
        bt.put(4, "二哈");
        bt.put(1, "张三");
        bt.put(3, "李四");
        bt.put(5, "王五");
        System.out.println(bt.size());
        bt.put(1, "老三");
        System.out.println(bt.get(1));
        System.out.println(bt.size());
        bt.delete(1);
        System.out.println(bt.size());
        System.out.println(bt.min());
        System.out.println(bt.max());*/

        BinaryTree<String, String> bt = new BinaryTree<>();
        bt.put("E", "5");
        bt.put("B", "2");
        bt.put("G", "7");
        bt.put("A", "1");
        bt.put("D", "4");
        bt.put("F", "6");
        bt.put("H", "8");
        bt.put("C", "3");
        bt.preErgodic().forEach(System.out::print);
        System.out.println();
        bt.midErgodic().forEach(System.out::print);
        System.out.println();
        bt.afterErgodic().forEach(System.out::print);
        System.out.println();
        bt.layerErgodic().forEach(System.out::print);

        System.out.println("max depth: " + bt.maxDepth());

    }
}
