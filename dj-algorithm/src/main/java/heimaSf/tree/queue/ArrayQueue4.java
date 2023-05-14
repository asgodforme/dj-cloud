package heimaSf.tree.queue;

import java.util.Iterator;

public class ArrayQueue4<E> implements Queue<E>, Iterable<E> {
    private E[] array;
    private int head = 0;
    // tail指针不存储元素，只能留出来
    private int tail = 0;

    public ArrayQueue4(int capacity) {
       capacity -= 1;
       capacity |= capacity >> 1;
       capacity |= capacity >> 2;
       capacity |= capacity >> 4;
       capacity |= capacity >> 8;
       capacity |= capacity >> 16;

        capacity += 1;

        this.array = (E[]) new Object[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail & array.length - 1] = value;
        tail++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head & array.length - 1];
        head++;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[tail & array.length - 1];
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
                E value = array[p & array.length - 1];
                p++;
                return value;
            }
        };
    }

    public static void main(String[] args) {
        ArrayQueue4<Integer> queue = new ArrayQueue4<>(4);
        queue.offer(1);
        queue.offer(2);
        for (int i : queue) {
            System.out.println(i);
        }

//        System.out.println(queue.peek());

    }
}
