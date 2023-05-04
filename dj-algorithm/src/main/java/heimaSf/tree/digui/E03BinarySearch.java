package heimaSf.tree.digui;

import org.junit.Assert;

public class E03BinarySearch {

    public static void main(String[] args) {
        int [] a = {7, 13, 21, 30, 38, 44, 52, 53};
        Assert.assertEquals(0, search(a, 7));
        Assert.assertEquals(1, search(a, 13));
        Assert.assertEquals(2, search(a, 21));
        Assert.assertEquals(3, search(a, 30));
        Assert.assertEquals(4, search(a, 38));
        Assert.assertEquals(5, search(a, 44));
        Assert.assertEquals(6, search(a, 52));
        Assert.assertEquals(7, search(a, 53));
    }


    public static int search(int[] a, int target) {
        return f(a, target, 0, a.length - 1);
    }

    private static int f(int[] a, int target, int i, int j) {
        if (i > j) {
            return -1;
        }
        int m = (i + j) >>> 1;
        if (target < a[m]) {
            return f(a, target, i, m - 1);
        } else if (a[m] < target) {
            return f(a, target, m + 1, j);
        } else {
            return m;
        }
    }
}
