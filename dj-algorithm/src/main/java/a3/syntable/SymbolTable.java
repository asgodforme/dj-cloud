package a3.syntable;

/**
 * 无序符号表
 * @param <Key>
 * @param <Value>
 */
public class SymbolTable<Key, Value> {
    private Node head; // 记录首节点
    private int n; // 记录符号表中的元素个数

    public SymbolTable() {
        head = new Node(null, null, null);
        n = 0;
    }

    public int size() {
        return n;
    }

    public void put(Key key, Value value) {
        Node n = head;

        // 先从符号表中查找键为key的键值对
        while (n.next != null) {
            n = n.next;
            if (n.key.equals(key)) {
                n.value = value;
                return;
            }
        }

        // 符号表中没有键为key的键值对
        Node oldFirst = head.next;
        Node newFirst = new Node(key, value, oldFirst);
        head.next = newFirst;
        this.n++;
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

    public static void main(String[] args) {
        SymbolTable<Integer, String> st = new SymbolTable<>();
        st.put(1, "张三");
        st.put(3, "李四");
        st.put(5, "王五");
        System.out.println(st.size());
        st.put(1, "jiangjie");
        System.out.println(st.get(1));
        System.out.println(st.size());
        st.delete(1);
        System.out.println(st.size());
    }
}
