package heimaSf.tree.Deque;

import java.util.Iterator;

public class ArrayDeque1<E> implements Deque<E>, Iterable<E> {

    static int inc(int i, int length) {
        if (i + 1 > length) {
            i = 0;
        }
        return i + 1;
    }

    static int dec(int i, int length) {
        if (i - 1 < 0) {
            return length -1;
        }
        return i - 1;
    }

    E[] array;
    int head;
    int tail;

    public ArrayDeque1(int capacity) {
        this.array = (E[]) new Object[capacity + 1];
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()) {
            return false;
        }
        head = dec(head, array.length);
        array[head] = e;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()) {
            return false;
        }
        array[tail] = e;
        tail = inc(tail, array.length);
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        E e = array[head];
        array[head] = null;
        head = inc(head, array.length);
        return e;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        E e = array[tail];
        array[tail] = null;
        tail = dec(tail, array.length);
        return e;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return array[dec(tail, array.length)];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        if (tail > head) {
            return tail - head == array.length - 1;
        } else if (head > tail) {
            return head - tail == 1;
        } else {
            return false;
        }
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
                E e = array[p];
                p = inc(p, array.length);
                return e;
            }
        };
    }

    public static void main(String[] args) {
        ArrayDeque1<Integer> deque1 = new ArrayDeque1<>(7);
        deque1.offerFirst(1);
        deque1.offerFirst(2);
        deque1.offerFirst(3);
        deque1.offerFirst(4);
        deque1.offerFirst(5);
        deque1.offerFirst(6);

        for (int i : deque1) {
            System.out.println(i);
        }
    }
}
