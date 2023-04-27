package heimaSf.tree.binarySearch;

public class E704 {

    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        System.out.println(binarySearch(nums, 9));
        System.out.println(binarySearch(nums, 2));
    }

    public static int binarySearch(int[] a, int target) {
        int i = 0, j = a.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }
}
