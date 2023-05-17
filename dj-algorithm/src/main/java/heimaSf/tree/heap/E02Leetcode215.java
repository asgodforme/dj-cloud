package heimaSf.tree.heap;

public class E02Leetcode215 {

    public static int findKthLargest(int[] numbers, int k) {
        MinHeap heap = new MinHeap(k);
        for (int i = 0; i < numbers.length; i++) {
            if (i < k) {
                heap.offer(numbers[i]);
            } else {
                if (heap.peek() < numbers[i]) {
                    heap.replace(numbers[i]);
                }
            }
        }
        return heap.peek();
    }

    public static int findKthLargest1(int[] numbers, int k) {
        MinHeap heap = new MinHeap(k);
        for (int i = 0; i < k; i++) {
            heap.offer(numbers[i]);
        }
        for (int i = 0; i < numbers.length; i++) {
            if (heap.peek() < numbers[i]) {
                heap.replace(numbers[i]);
            }
        }
        return heap.peek();
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest1(new int[] {3, 2, 1, 5, 6, 4}, 2));
        System.out.println(findKthLargest1(new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }
}
