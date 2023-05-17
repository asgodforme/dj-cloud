package heimaSf.tree.heap;

public class E03Leetcode703 {

    private MinHeap heap;

    public E03Leetcode703(int k, int[] nums) {
        heap = new MinHeap(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (!heap.isFull()) {
            heap.offer(val);
        } else if (val > heap.peek()) {
            heap.replace(val);
        }
        return heap.peek();
    }

    public static void main(String[] args) {
        E03Leetcode703 e03Leetcode703 = new E03Leetcode703(3, new int[]{4, 5, 8, 2});
        System.out.println(e03Leetcode703.add(3));
        System.out.println(e03Leetcode703.add(5));
        System.out.println(e03Leetcode703.add(10));
        System.out.println(e03Leetcode703.add(9));
        System.out.println(e03Leetcode703.add(4));
    }
}
