package heimaSf.tree.heap;

import java.util.Arrays;

public class Heap {
    int[] array;
    int size;
    boolean max;

    public Heap(int capacity) {
        this.array = new int[capacity];
    }

    public Heap(int capacity, boolean max) {
        this.array = new int[capacity];
        this.max = max;
    }

    public int size() {
        return size;
    }

    /**
     * 移除堆顶元素
     *
     * @return
     */
    public int poll() {
        int top = array[0];
        swap(0, size - 1);
        size--;
        down(0);
        return top;
    }

    public int poll(int index) {
        int deleted = array[index];
        swap(index, size - 1);
        size--;
        down(index);
        return deleted;
    }

    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    /**
     * 获取堆顶元素
     *
     * @return
     */
    public int peek() {
        return array[0];
    }

    public boolean offer(int offered) {
        if (size == array.length) {
            grow();
        }
        up(offered);
        size++;
        return true;
    }

    private void grow() {
        int capacity = size + (size >> 1);
        int[] newArray = new int[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void up(int offered) {
        int child = size;
        while (child > 0) {
            int parent = (child - 1) / 2;
            boolean cmp = max ? offered > array[parent] : offered < array[parent];
            if (cmp) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child] = offered;
    }

    private void down(int parent) {
        int left = 2 * parent + 1;
        int right = left + 1;
        int target = parent;
        if (left < size && (max ? array[left] > array[target] : array[left] < array[target])) {
            target = left;
        }
        if (right < size && (max ? array[right] > array[target] : array[right] < array[target])) {
            target = right;
        }
        if (target != parent) {
            swap(target, parent);
            down(target);
        }
    }

    private void swap(int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void main(String[] args) {
        Heap heap = new Heap(5);
        for (int i = 0; i < 10; i++) {
            heap.offer(i);
            System.out.println(Arrays.toString(heap.array));
        }
    }
}
