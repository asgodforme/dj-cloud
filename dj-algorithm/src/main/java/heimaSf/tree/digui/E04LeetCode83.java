package heimaSf.tree.digui;

public class E04LeetCode83 {

    public static ListNode deleteDuplicate1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p1 = head;
        ListNode p2;
        while ((p2 = p1.next) != null) {
            if (p1.value == p2.value) {
                p1.next = p2.next;
            } else {
                p1 = p1.next;
            }
        }
        return head;
    }

    public static ListNode deleteDuplicate2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.value == head.next.value) {
            return deleteDuplicate2(head.next);
        } else {
            head.next = deleteDuplicate2(head.next);
            return head;
        }
    }

    public static ListNode deleteDuplicate3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.value == head.next.value) {
            ListNode x = head.next.next;
            while (x != null && x.value == head.value) {
                x = x.next;
            }
            return deleteDuplicate3(x);
        } else {
            head.next = deleteDuplicate3(head.next);
            return head;
        }
    }

    public static ListNode deleteDuplicate(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2, p3;
        while ((p2 = p1.next) != null && (p3 = p2.next) != null) {
            if (p2.value == p3.value) {
                while ((p3 = p3.next) != null && p3.value == p2.value) {

                }
                p1.next = p3;
            } else {
                p1 = p1.next;
            }
        }
        return s.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 1, 2, 3, 3);
        System.out.println(head);
        System.out.println(deleteDuplicate(head));
    }
}
