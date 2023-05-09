package heimaSf.tree.digui;

public class E08LeetCode876 {

    public static ListNode middleNode(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        System.out.println(p1);
        return p1;
    }

    public static ListNode reverse(ListNode head) {
        ListNode n1 = null;
        ListNode o1 = head;
        while (o1 != null) {
            ListNode o2 = o1.next;
            o1.next = n1;
            n1 = o1;
            o1 = o2;
        }
        System.out.println(n1);
        return n1;
    }

    public static boolean isPalidrome1(ListNode head) {
        ListNode middle = reverse(middleNode(head));
        while (middle != null) {
            if (middle.value != head.value) {
                return false;
            }
            middle = middle.next;
            head = head.next;
        }
        return true;

    }

    public static boolean isPalidrome(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        ListNode o1 = head;
        ListNode n1 = null;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;

            o1.next = n1;
            n1 = o1;
            o1 = p1;
        }

        if (p2 != null) {
            p1 = p1.next;
        }

        while (n1 != null) {
            if (n1.value != p1.value) {
                return false;
            }
            n1 = n1.next;
            p1 = p1.next;
        }

        return true;

    }

    public static void main(String[] args) {
//        ListNode lists1 = ListNode.of(1, 2, 3, 4, 5);
//        System.out.println(middleNode(lists1));
//        ListNode lists2 = ListNode.of(1, 2, 3, 4, 5, 6);
//        System.out.println(middleNode(lists2));

        ListNode lists1 = ListNode.of(1, 2, 2, 1);
        System.out.println(isPalidrome(lists1));
        ListNode lists2 = ListNode.of(1, 2, 3, 2, 1);
        System.out.println(isPalidrome(lists2));


        ListNode lists3 = ListNode.of(1, 2, 3, 1);
        System.out.println(isPalidrome(lists3));
        ListNode lists4 = ListNode.of(1, 2, 3, 4, 1);
        System.out.println(isPalidrome(lists4));

    }
}
