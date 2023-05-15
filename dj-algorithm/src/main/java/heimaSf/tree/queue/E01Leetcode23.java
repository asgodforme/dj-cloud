package heimaSf.tree.queue;

import heimaSf.tree.digui.ListNode;

public class E01Leetcode23 {

    public static ListNode mergeKLists(ListNode[] lists) {
        MinHeap minHeap = new MinHeap(lists.length);
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }


        ListNode s = new ListNode(-1, null);
        ListNode t = s;
        while (!minHeap.isEmpty()) {
            ListNode min = minHeap.poll();
            t.next = min;
            t = min;

            if (min.next != null) {
                minHeap.offer(min.next);
            }
        }
        return s.next;

    }

    public static void main(String[] args) {
        ListNode[] lists = {
                ListNode.of(1, 4, 5),
                ListNode.of(1, 3, 4),
                ListNode.of(2, 6),
                null
        };
        ListNode m = mergeKLists(lists);
        System.out.println(m);
    }

}
