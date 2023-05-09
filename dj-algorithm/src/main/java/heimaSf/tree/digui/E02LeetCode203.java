package heimaSf.tree.digui;

public class E02LeetCode203 {
    public static ListNode removeElements1(ListNode head, int val) {
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2 = s.next;
        while (p2 != null) {
            if (p2.value == val) {
                p1.next = p2.next;
                p2 = p2.next;
            } else {
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        return s.next;
    }

    public static ListNode removeElements2(ListNode head, int val) {
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2;
        while ((p2 = p1.next) != null) {
            if (p2.value == val) {
                p1.next = p2.next;
            } else {
                p1 = p1.next;
            }
        }
        return s.next;
    }

    public static ListNode removeElements(ListNode head, int val) {
        return rmRec(head, val);
    }

    public static ListNode rmRec(ListNode node, int val) {
        if (node == null) {
            return null;
        }
        if (node.value == val) {
            return rmRec(node.next, val);
        } else {
            node.next = removeElements(node.next, val);
            return node;
        }
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 2, 6, 3, 6);
        System.out.println(head);
        removeElements(head, 6);
        System.out.println(head);
    }
}
