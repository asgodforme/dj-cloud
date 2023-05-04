package heimaSf.tree.digui;

public class E206ReverseLinkedList1 {
    public static ListNode reverseList1(ListNode node) {
        ListNode n1 = null;
        ListNode o1 = node;
        while (o1 != null) {
            n1 = new ListNode(o1.value, n1);
            o1 = o1.next;
        }
        return n1;
    }

    static class List {
        ListNode head;

        public List(ListNode head) {
            this.head = head;
        }

        public void addFirst(ListNode node) {
            node.next = head;
            head = node;
        }

        public ListNode removeFirst() {
            ListNode o1 = head;
            if (o1 != null) {
                head = o1.next;
            }
            return o1;
        }
    }

    public static ListNode reverseList2(ListNode node) {
        List o1 = new List(node);
        List n1 = new List(null);
        while (true) {
            ListNode first = o1.removeFirst();
            if (first == null) {
                break;
            }
            n1.addFirst(first);
        }
        return n1.head;
    }

    public static ListNode reverseList3(ListNode node) {
        if (node.next == null) {
            return node;
        }
        ListNode last = reverseList(node.next);
        node.next.next = node;
        node.next = null;
        return last;
    }

    public static ListNode reverseList4(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode o2 = node.next;
        ListNode n1 = node;
        while (o2 != null) {
            node.next = o2.next;
            o2.next = n1;
            n1 = o2;
            o2 = node.next;
        }
        return n1;
    }

    public static ListNode reverseList(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode o1 = node;
        ListNode n1 = null;
        while (o1 != null) {
            ListNode o2 = o1.next;
            o1.next = n1;
            n1 = o1;
            o1 = o2;
        }
        return n1;
    }

    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        System.out.println(reverseList(o1));

    }
}
