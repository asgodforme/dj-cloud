import java.util.Arrays;

/*
递增递减数组中，给定目标值，返回该目标值在数组中的下标。如果有多个数据满足条件，返回最小的下标。
示例 1：
输入：array = [1,5,7,8,13,7,2], target = 7
输出：2
解释：7 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。
示例 2：
输入：array = [0,5,9,13,2,1], target = 3
输出：-1
解释：3 在数组中没有出现，返回 -1。
interface BiggerSmallerArray {
    public int get(int index);][
    public int length();
}
class Main {
    public int findInBiggerSmallerArray(int target, BiggerSmallerArray biggerSmallerArray) {
    }
}

 */
public class Test {

    public static void main(String[] args) {
        int[] arrays = {1,5,7,8,13,7,2};
        int target = 7;
//        int[] arrays = {0,5,9,13,2,1};
//        int target = 3;
        BiggerSmallerArray biggerSmallerArray = new BinarySearch(arrays);
        System.out.println(findInBiggerSmallerArray(target, biggerSmallerArray));

    }

    interface BiggerSmallerArray {
        int get(int index);
        int length();
    }

    static class BinarySearchPlus implements BiggerSmallerArray {

        private int[] arrays;
        private int start;
        private int end;

        public BinarySearchPlus(int[] arrays, int start, int end) {
            this.arrays = arrays;
            this.start = start;
            this.end = end;
        }

        @Override
        public int get(int index) {
            return arrays[index];
        }

        @Override
        public int length() {
            return arrays.length;
        }

        public int[] getArrays() {
            return arrays;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }

    static class BinarySearch implements BiggerSmallerArray {

        public int[] arrays;

        public BinarySearch(int[] arrays) {
            this.arrays = arrays;
        }

        @Override
        public int get(int index) {
            return arrays[index];
        }

        @Override
        public int length() {
            return arrays.length;
        }

        public int[] getArrays() {
            return arrays;
        }
    }


    public static int findInBiggerSmallerArray(int target, BiggerSmallerArray biggerSmallerArray) {
        BinarySearch binarySearch = (BinarySearch) biggerSmallerArray;

        if (binarySearch.get(0) > target || binarySearch.get(binarySearch.length() - 1) > target) {
            return -1;
        }
        int length = binarySearch.length();
        int mid = length / 2;
        int midValue = binarySearch.get(mid);

        if (midValue == target) {
            return mid;
        }

        int start = mid;
        while (midValue > target && start != 0) {
            if (binarySearch.get(start) == target) {
               return start;
            }
            start--;
        }

        start = mid;
        while (midValue < target && start != binarySearch.length()) {
            if (binarySearch.get(start) == target) {
                return start;
            }
            start++;
        }
        return -1;
    }

    public static int findInBiggerSmallerArray1(int target, BiggerSmallerArray biggerSmallerArray) {
        BinarySearchPlus binarySearch = (BinarySearchPlus) biggerSmallerArray;

        if (binarySearch.get(0) > target || binarySearch.get(binarySearch.length() - 1) > target) {
            return -1;
        }
        int length = binarySearch.length();
        int mid = length / 2;
        int midValue = binarySearch.get(mid);

        if (midValue == target) {
            return mid;
        }

        if (midValue > target) {
            binarySearch = new BinarySearchPlus(binarySearch.getArrays(), 0, mid);
        }


        return -1;
    }
}
