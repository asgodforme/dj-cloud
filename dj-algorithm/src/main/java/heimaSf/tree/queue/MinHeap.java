package heimaSf.tree.queue;

import heimaSf.tree.digui.ListNode;

/**
 * 小顶堆
 */
public class MinHeap {

    ListNode[] array;
    int size;

    public MinHeap(int capacity) {
        this.array = new ListNode[capacity];
    }

    public boolean offer(ListNode value) {
        if (isFull()) {
            return false;
        }
        // 新加入的元素放到末尾， 但不马上放到数组中。
        int child = size++;
        // 先去上浮判断优先级
        int parent = (child - 1) / 2;
        while (child > 0 && value.value < array[parent].value) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = value;
        return true;
    }

    public ListNode poll() {
        if (isEmpty()) {
            return null;
        }
        // 交换首尾元素，让优先级的元素换到数组末尾
        swap(0, size - 1);
        size--;
        ListNode e = array[size];
        array[size] = null; // help GC

        // 下潜
        down(0);

        return e;
    }

    private void down(int parent) {
        int left = 2 * parent + 1;
        int right = left + 1;
        int max = parent; // 假设福元素的优先级最高
        if (left < size && array[left].value < array[max].value) {
            max = left;
        }
        if (right < size && array[right].value < array[max].value) {
            max = right;
        }
        if (max != parent) {
            swap(max, parent);
            down(max);
        }
    }

    public void swap(int i, int j) {
        ListNode t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }
}
