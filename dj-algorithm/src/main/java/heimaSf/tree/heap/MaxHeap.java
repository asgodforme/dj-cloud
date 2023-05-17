package heimaSf.tree.heap;

import java.util.Arrays;

public class MaxHeap {
    int[] array;
    int size;

    public MaxHeap(int capacity) {
        this.array = new int[capacity];
    }

    public MaxHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        this.headify();
    }

    /**
     * 1.找到最后一个非叶子节点
     * 2.从后向前，对每个节点执行下潜
     */
    private void headify() {
        // 找到最后一个非叶子节点  size /2 - 1
        for (int i = size / 2 -1; i >= 0; i--) {
            down(i);
        }
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
            return false;
        }
        up(offered);
        size++;
        return true;
    }

    private void up(int offered) {
        int child = size;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (offered > array[parent]) {
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
        int max = parent;
        if (left < size && array[left] > array[max]) {
            max = left;
        }
        if (right < size && array[right] > array[max]) {
            max = right;
        }
        if (max != parent) {
            swap(max, parent);
            down(max);
        }
    }

    private void swap(int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        MaxHeap maxHeap = new MaxHeap(array);
        System.out.println(Arrays.toString(maxHeap.array));

        while (maxHeap.size > 1) {
            maxHeap.swap(0, maxHeap.size - 1);
            maxHeap.size--;
            maxHeap.down(0);
        }

        System.out.println(Arrays.toString(maxHeap.array));
    }
}
