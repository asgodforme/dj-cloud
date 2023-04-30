package heimaSf.tree.linkedList;

import org.junit.Test;

import java.util.Iterator;

public class DoubleLinkedListSentinel implements Iterable<Integer> {

    private Node sentinel = new Node(null, -1, null);

    public DoubleLinkedListSentinel() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(int value) {
        Node a = sentinel;
        Node b = sentinel.next;
        Node added = new Node(a, value, b);
        a.next = added;
        b.prev = added;
    }

    public void addLast(int value) {
        Node a = sentinel.prev;
        Node b = sentinel;
        Node added = new Node(a, value, b);
        a.next = added;
        b.prev = added;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = sentinel.next;

            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    public void removeFirst() {
        Node removed = sentinel.next;
        if (removed == sentinel) {
            throw new IllegalArgumentException();
        }
        Node a = sentinel;
        Node b = removed.next;
        a.next = b;
        b.prev = a;
    }

    public void removeLast() {
        Node removed = sentinel.prev;
        if (removed == sentinel) {
            throw new IllegalArgumentException();
        }
        Node a = removed.prev;
        Node b = sentinel;
        a.next = b;
        b.prev = a;
    }

    private static class Node {
        Node prev;
        int value;
        Node next;

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    public void removeByValue(int value) {
        Node removed = findByValue(value);
        if (removed == null) {
            throw new IllegalArgumentException();
        }
        Node a = removed.prev;
        Node b = removed.next;
        a.next = b;
        b.prev = a;
    }

    private Node findByValue(int value) {
        Node p = sentinel.next;
        while (p != sentinel) {
            if (p.value == value) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    @Test
    public void test1() {
        DoubleLinkedListSentinel integers = new DoubleLinkedListSentinel();
        integers.addFirst(1);
        integers.addFirst(2);
        integers.addFirst(3);
        integers.addFirst(4);
        integers.addFirst(5);
        integers.removeFirst();
        for (Integer value : integers) {
            System.out.println(value);
        }
    }

    @Test
    public void test2() {
        DoubleLinkedListSentinel integers = new DoubleLinkedListSentinel();
        integers.addLast(1);
        integers.addLast(2);
        integers.addLast(3);
        integers.addLast(4);
        integers.addLast(5);
        integers.removeLast();
        for (Integer value : integers) {
            System.out.println(value);
        }
    }

    @Test
    public void test3() {
        DoubleLinkedListSentinel integers = new DoubleLinkedListSentinel();
        integers.addLast(1);
        integers.addLast(2);
        integers.addLast(3);
        integers.addLast(4);
        integers.addLast(5);
        integers.removeByValue(3);
        for (Integer value : integers) {
            System.out.println(value);
        }
    }
}
