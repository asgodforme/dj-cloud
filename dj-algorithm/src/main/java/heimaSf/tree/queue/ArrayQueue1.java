package heimaSf.tree.queue;

import java.util.Iterator;

public class ArrayQueue1<E> implements Queue<E>, Iterable<E> {
    private E[] array;
    private int head = 0;
    // tail指针不存储元素，只能留出来
    private int tail = 0;

    public ArrayQueue1(int capacity) {
        this.array = (E[]) new Object[capacity + 1];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;
        tail = (tail + 1) % array.length;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head];
        head = (head + 1) % array.length;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head];
        return value;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % array.length == head;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
                return value;
            }
        };
    }

    public static void main(String[] args) {
        ArrayQueue1<Integer> queue = new ArrayQueue1<>(1);
        queue.offer(1);
        queue.offer(2);
        for (int i : queue) {
            System.out.println(i);
        }

//        System.out.println(queue.peek());

    }
}
