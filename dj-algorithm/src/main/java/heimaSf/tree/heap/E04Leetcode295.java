package heimaSf.tree.heap;

import java.util.Arrays;

public class E04Leetcode295 {

    public void addNum(int num) {
        // 左右个数一致，让左边+1
        if (left.size == right.size) {
            right.offer(num);
            left.offer(right.poll());
        } else {
            left.offer(num);
            right.offer(left.poll());
        }
    }

    public double findMedium() {
        if (left.size == right.size) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return left.peek();
        }
    }

    @Override
    public String toString() {
        int size = left.size;
        int[] left = new int[size];
        for (int i = 0; i < left.length; i++) {
            left[size - 1 - i] = this.left.array[i];
        }
        int[] rigth = Arrays.copyOf(this.right.array, this.right.size);
        return Arrays.toString(left) + " < - > " + Arrays.toString(rigth);
    }

    private Heap left = new Heap(10, true);
    private Heap right = new Heap(10, false);

    public static void main(String[] args) {
        E04Leetcode295 test = new E04Leetcode295();
        test.addNum(1);
        System.out.println(test + ", " + test.findMedium());
        test.addNum(2);
        System.out.println(test + ", " + test.findMedium());
        test.addNum(3);
        System.out.println(test + ", " + test.findMedium());
        test.addNum(7);
        System.out.println(test + ", " + test.findMedium());
        test.addNum(8);
        System.out.println(test + ", " + test.findMedium());
        test.addNum(9);
        System.out.println(test + ", " + test.findMedium());
    }
}
