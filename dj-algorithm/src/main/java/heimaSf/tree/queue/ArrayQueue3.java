package heimaSf.tree.queue;

import java.util.Iterator;

public class ArrayQueue3<E> implements Queue<E>, Iterable<E> {
    private E[] array;
    private int head = 0;
    // tail指针不存储元素，只能留出来
    private int tail = 0;

    public ArrayQueue3(int capacity) {
        this.array = (E[]) new Object[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[(int) (Integer.toUnsignedLong(tail) % array.length)] = value;
        tail++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = array[(int) (Integer.toUnsignedLong(head) % array.length)];
        head++;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[(int) (Integer.toUnsignedLong(head) % array.length)];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return tail - head == array.length;
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
                E value = array[(int) (Integer.toUnsignedLong(p) % array.length)];
                p++;
                return value;
            }
        };
    }

    public static void main(String[] args) {
        ArrayQueue3<Integer> queue = new ArrayQueue3<>(3);
        queue.offer(1);
        queue.offer(2);
        for (int i : queue) {
            System.out.println(i);
        }

//        System.out.println(queue.peek());

    }
}
