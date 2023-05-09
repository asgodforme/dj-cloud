package heimaSf.tree.digui;

public class E11LeetCode142 {

    public ListNode detectCycle(ListNode head) {
        ListNode h = head;
        ListNode t = head;
        while (h != null && h.next != null) {
            t = t.next;
            h = h.next.next;

            if (h == t) {
                t = head;
                while (true) {
                    if (h == t) {
                        return t;
                    }
                    t = t.next;
                    h = h.next;
                }
            }
        }
        return null;
    }
}
