package heimaSf.tree.binarySearch;

import java.util.Arrays;
import java.util.List;

public class E34 {
    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        System.out.println(searchRange(nums, 8));
        System.out.println(searchRange(nums, 6));
        int[] nums1 = {};
        System.out.println(searchRange(nums1, 0));

    }

    public static List<Integer> searchRange(int[] a, int target) {
//        return Arrays.asList(searchLeft(a, target), searchRight(a, target));
        int x = searchLeft(a, target);
        if (x == -1) {
            return Arrays.asList(-1, -1);
        } else {
            return Arrays.asList(x, searchRight(a, target));
        }
    }

    public static int searchLeft(int[] a, int target) {
        int i = 0, j = a.length - 1;
        int candidate = -1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if ( a[m] < target) {
                i = m + 1;
            } else {
                candidate = m;
                j = m - 1;
            }
        }
        return candidate;
    }

    public static int searchLeft1(int[] a, int target) {
        int i = 0, j = a.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target <= a[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return i;
    }

    public static int searchRight(int[] a, int target) {
        int i = 0, j = a.length - 1;
        int candidate = -1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if ( a[m] < target) {
                i = m + 1;
            } else {
                candidate = m;
                i = m + 1;
            }
        }
        return candidate;
    }

    public static int searchRight1(int[] a, int target) {
        int i = 0, j = a.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return i - 1;
    }
}
