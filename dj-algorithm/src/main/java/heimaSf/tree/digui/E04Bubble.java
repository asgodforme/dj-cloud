package heimaSf.tree.digui;

import java.util.Arrays;

public class E04Bubble {
    public static void main(String[] args) {
        int[] a = {1, 3, 9, 7, 3, 2, 0};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));

    }

    public static void sort(int[] a) {
        bubble(a, a.length - 1);
    }

    private static void bubble(int[] a, int j) {
        if (j == 0) {
            return;
        }
        // x是有序区和无序区的分界线
        int x = 0;
        for (int i = 0; i < j; i++) {
            if (a[i] > a[i + 1]) {
                int t = a[i];
                a[i] = a[i + 1];
                a[i + 1]  = t;
                x = i;
            }
        }
        bubble(a, x);
    }
}
