package a3.syntable;

import org.springframework.core.NativeDetector;

/**
 * 有序符号表
 *
 * @param <Key>
 * @param <Value>
 */
public class OrderSymbolTable<Key extends Comparable<Key>, Value> {
    private Node head;
    private int n;

    public OrderSymbolTable() {
        head = new Node(null, null, null);
        n = 0;
    }

    public int size() {
        return n;
    }

    public void put(Key key, Value value) {
        Node current = head.next;
        Node pre = head;
        while (current != null && key.compareTo(current.key) > 0) {
            pre = current;
            current = current.next;
        }
        if (current != null && current.key.compareTo(key) == 0) {
            current.value = value;
            return;
        }
        Node node = new Node(key, value, current);
        pre.next = node;
    }

    public void delete(Key key) {
        Node n = head;
        while (n.next != null) {
            if (n.next.key.equals(key)) {
                n.next = n.next.next;
                this.n--;
                return;
            }
            n = n.next;
        }
    }

    public Value get(Key key) {
        Node n = head;
        while (n.next != null) {
            n = n.next;
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    private class Node {
        public Key key;
        public Value value;
        public Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) throws Exception {
        OrderSymbolTable<Integer, String> bt = new OrderSymbolTable<>();
        bt.put(4, "二哈");
        bt.put(3, "张三");
        bt.put(1, "李四");
        bt.put(1, "aa");
        bt.put(5, "王五");
    }
}
