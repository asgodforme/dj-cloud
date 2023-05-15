package heimaSf.tree.queue;

public class PriorityQueue1<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue1(int capacity) {
        this.array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[size++] = value;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        int max = selectMax();
        E e = (E) array[max];
        remove(max);
        return e;
    }

    private void remove(int index) {
        if (index < size - 1) {
            // 0 1 2 3 4 5
            // 1 2 3 4 5 6
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
        }
        size--;
    }

    private int selectMax() {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].priority() > array[max].priority()) {
                max = i;
            }
        }
        return max;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[selectMax()];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }

    public static void main(String[] args) {
        PriorityQueue1<Entry> queue = new PriorityQueue1<>(5);
        queue.offer(new Entry("task1", 4));
        queue.offer(new Entry("task1", 3));
        queue.offer(new Entry("task1", 2));
        queue.offer(new Entry("task1", 5));
        queue.offer(new Entry("task1", 1));

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
