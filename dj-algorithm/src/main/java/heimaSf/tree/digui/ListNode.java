package heimaSf.tree.digui;

public class ListNode {
    public int value;
    public ListNode next;

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    public static ListNode of(int ...elements) {
        if (elements.length == 0) {
            return null;
        }
        ListNode head = new ListNode(elements[0], null);
        ListNode p = head;
        for (int i = 1; i < elements.length; i++) {
            ListNode node = new ListNode(elements[i], null);
            p.next = node;
            p = node;
        }
        return head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("[");
        ListNode current = this;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(",");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
