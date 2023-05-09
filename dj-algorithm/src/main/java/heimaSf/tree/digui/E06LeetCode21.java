package heimaSf.tree.digui;

public class E06LeetCode21 {

    public static ListNode mergeTwoLists1(ListNode p1, ListNode p2) {
        ListNode s = new ListNode(-1, null);
        ListNode p = s;
        while (p1 != null && p2 != null) {
            if (p1.value < p2.value) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        return s.next;
    }

    public static ListNode mergeTwoLists(ListNode p1, ListNode p2) {
        if (p1 == null) {
            return p2;
        }
        if (p2 == null) {
            return p1;
        }
        if (p1.value < p2.value) {
            p1.next = mergeTwoLists(p1.next, p2);
            return p1;
        } else {
            p2.next = mergeTwoLists(p1, p2.next);
            return p2;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return split(lists, 0, lists.length - 1);
    }

    public static ListNode split(ListNode[] lists, int i, int j) {
        if (i == j) {
            return lists[i];
        }
        int m = (i + j) >>> 1;
        ListNode p1 = split(lists, i, m);
        ListNode p2 = split(lists, m + 1, j);
        return mergeTwoLists1(p1, p2);
    }

    public static void main(String[] args) {
        ListNode p1 = ListNode.of(1, 3, 8, 9);
        ListNode p2 = ListNode.of(2, 4);
        System.out.println(mergeTwoLists(p1, p2));

        ListNode[] lists = {ListNode.of(1, 4, 5), ListNode.of(1, 3, 4), ListNode.of(2, 6)};
        System.out.println(mergeKLists(lists));
    }
}
