package heimaSf.tree.digui;

public class E206ReverseLinkedList {

    private ListNode head;

    private static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

    public void addFirst(int n) {
        head = new ListNode(n, head);
    }

    public void reverse() {
        rec(head);
    }

    private void rec(ListNode node) {
        if (node == null) {
            return;
        }
        rec(node.next);
        System.out.println(node.value);
    }

    public static void main(String[] args) {
        E206ReverseLinkedList list = new E206ReverseLinkedList();
        list.addFirst(5);
        list.addFirst(4);
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        list.reverse();
    }
}
