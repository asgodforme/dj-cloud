package heimaSf.tree.linkedList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class SinglyLinkedList implements Iterable<Integer> {

    private Node head = null;

    public void addFirst(int value) {
        // 兼容处理链表为空和链表不为空的情况
        head = new Node(value, head);
    }

    public void loop1(Consumer<Integer> consumer) {
        Node p = head;
        while (p != null) {
            consumer.accept(p.value);
            p = p.next;
        }
    }

    public void loop2(Consumer<Integer> consumer) {
        for (Node p = head; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new NodeIterator();
    }

    /**
     * 当内部类的成员变量引用了外部类的成员变量时，此时与外部类的实例变量相关，不能使用static
     *
     * 如果内部类与外部类不相关，相对比较独立，则使用static
     */
    private class NodeIterator implements Iterator<Integer> {
        Node p = head;

        @Override
        public boolean hasNext() {
            return p != null;
        }

        @Override
        public Integer next() {
            int value = p.value;
            p = p.next;
            return value;
        }
    }

    private static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node findLast() {
        if (head == null) {
            return null;
        }
        Node p;
        for (p = head; p.next != null; p = p.next) {

        }
        return p;
    }

    public void addLast(int value) {
        Node last = findLast();
        if (last == null) {
            addFirst(value);
            return;
        }
        last.next = new Node(value, null);
    }

    private Node findNode(int index) {
        int i = 0;
        for (Node p = head; p != null; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    public int get(int index) {
        Node node = findNode(index);
        if (node == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法", index));
        }
        return node.value;
    }

    public void insert(int index, int value) {
        if (index == 0) {
            addFirst(value);
            return;
        }
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法", index));
        }
        prev.next = new Node(value, prev.next);
    }

    public void removeFirst() {
        if (head == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法", 0));
        }
        head = head.next;
    }

    public void remove(int index) {
        if (index == 0) {
            removeFirst();
            return;
        }
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法", index));
        }
        Node removed = prev.next;
        if (removed == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法", index));
        }
        prev.next = removed.next;
    }

    @Test
    public void test1() {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.addFirst(1);
        singlyLinkedList.addFirst(2);
        singlyLinkedList.addFirst(3);
        singlyLinkedList.addFirst(4);
        singlyLinkedList.addFirst(5);

//        singlyLinkedList.loop1(System.out::println);
//        singlyLinkedList.loop2(System.out::println);
        singlyLinkedList.insert(2, 6);
        singlyLinkedList.removeFirst();
        singlyLinkedList.remove(0);

        for (Integer value: singlyLinkedList) {
            System.out.println(value);
        }
    }

    @Test
    public void test2() {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.addLast(1);
        singlyLinkedList.addLast(2);
        singlyLinkedList.addLast(3);
        singlyLinkedList.addLast(4);
        singlyLinkedList.addLast(5);

        System.out.println(singlyLinkedList.get(0));
        System.out.println(singlyLinkedList.get(10));
    }
}
