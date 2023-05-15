package heimaSf.tree.queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue1<E> implements BlockingQueue<E> {
    private E[] array;
    private int size;
    private int head;
    private int tail;

    public BlockingQueue1(int capacity) {
        this.array = (E[]) new Object[capacity];
    }

    private ReentrantLock lock = new ReentrantLock();
    private Condition headAwait = lock.newCondition();
    private Condition tailAwait = lock.newCondition();

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    @Override
    public void offer(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isFull()) {
                tailAwait.await();
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
            headAwait.signal();
        } finally {
            lock.unlock();
        }

    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            long t = TimeUnit.MILLISECONDS.toMillis(timeout);
            while (isFull()) {
                if (t <= 0) {
                    return false;
                }
                t = tailAwait.awaitNanos(t);
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
            headAwait.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isEmpty()) {
                headAwait.await();
            }
            E e = array[head];
            if (++head == array.length) {
                head = 0;
            }
            size--;
            tailAwait.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }
}
