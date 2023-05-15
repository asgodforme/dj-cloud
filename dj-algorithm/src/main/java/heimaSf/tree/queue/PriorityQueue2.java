package heimaSf.tree.queue;

public class PriorityQueue2<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue2(int capacity) {
        this.array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        insert(value);
        size++;
        return true;
    }

    private void insert(E value) {
        int i = size - 1;
        while (i >= 0 && array[i].priority() > value.priority()) {
            array[i + 1] = array[i];
            i--;
        }
        array[i + 1] = value;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E e = (E) array[size - 1];
        array[--size] = null;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[size - 1];
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
        PriorityQueue2<Entry> queue = new PriorityQueue2<>(5);
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
