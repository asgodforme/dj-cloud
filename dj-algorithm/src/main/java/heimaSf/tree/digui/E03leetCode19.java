package heimaSf.tree.digui;

public class E03leetCode19 {

    public static ListNode removeNthWithEnd1(ListNode head, int n) {
        ListNode s = new ListNode(-1, head);
        recursion(s, n);
        return s.next;
    }


    public static int recursion(ListNode p, int n) {
        if (p == null) {
            return 0;
        }
        int nth = recursion(p.next, n);
        System.out.println(p.value + ": " + nth);
        if (nth == n) {
            p.next = p.next.next;
        }
        return nth + 1;

    }

    public static ListNode removeNthWithEnd(ListNode head, int n) {
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2 = p1;
        int step = n + 1;
        while (p2 != null) {
            if (step-- <= 0) {
                p1 = p1.next;
            }
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return s.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 2, 3, 4, 5);
        System.out.println(head);
        System.out.println(removeNthWithEnd(head, 2));
    }
}
