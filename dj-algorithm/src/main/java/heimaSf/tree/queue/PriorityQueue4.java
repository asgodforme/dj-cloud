package heimaSf.tree.queue;

public class PriorityQueue4<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue4(int capacity) {
        this.array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        // 新加入的元素放到末尾， 但不马上放到数组中。
        int child = size++;
        // 先去上浮判断优先级
        int parent = (child - 1) / 2;
        while (child > 0 && value.priority() > array[parent].priority()) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = value;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        // 交换首尾元素，让优先级的元素换到数组末尾
        swap(0, size - 1);
        size--;
        Priority e = array[size];
        array[size] = null; // help GC

        // 下潜
        down(0);

        return (E) e;
    }

    private void down(int parent) {
        int left = 2 * parent + 1;
        int right = left + 1;
        int max = parent; // 假设福元素的优先级最高
        if (left < size && array[left].priority() > array[max].priority()) {
            max = left;
        }
        if (right < size && array[right].priority() > array[max].priority()) {
            max = right;
        }
        if (max != parent) {
            swap(max, parent);
            down(max);
        }
    }

    public void swap(int i, int j) {
        Priority t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    @Override
    public E peek() {
        return (E) array[0];
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
        PriorityQueue4<Entry> queue = new PriorityQueue4<>(5);
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
