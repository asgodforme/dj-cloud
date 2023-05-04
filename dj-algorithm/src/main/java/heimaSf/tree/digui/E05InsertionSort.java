package heimaSf.tree.digui;

import java.util.Arrays;

public class E05InsertionSort {
    public static void main(String[] args) {
        int[] a = {1, 3, 9, 7, 3, 2, 0};
        System.out.println(Arrays.toString(a));
        insertion(a, 1);
        System.out.println(Arrays.toString(a));
    }

    private static void insertion(int[] a, int low) {
        if (low == a.length) {
            return;
        }

        int t = a[low];
        int i = low - 1;

        while (i >= 0 && a[i] > t) {
            a[i + 1] = a[i];
            i--;
        }

        if (i + 1 != low) {
            a[i + 1] = t;
        }
        insertion(a, low + 1);
    }
}
