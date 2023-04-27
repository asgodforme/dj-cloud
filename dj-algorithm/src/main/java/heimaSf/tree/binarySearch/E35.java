package heimaSf.tree.binarySearch;

public class E35 {
    public static void main(String[] args) {
        int[] a = {1, 3, 5, 6};
        System.out.println(searchIndex(a, 5));
        System.out.println(searchIndex(a, 2));
        System.out.println(searchIndex(a, 7));
    }

    public static int searchIndex(int[] a, int target) {
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
}
