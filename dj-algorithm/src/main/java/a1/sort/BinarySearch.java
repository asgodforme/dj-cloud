package a1.sort;

import java.util.Arrays;

public class BinarySearch {

    public static boolean search(int[] target, int item) {
        int low = 0;
        int high = target.length - 1;
        int mid;
        int guess;
        while (low <= high) {
            mid = (low + high) / 2;
            guess = target[mid];
            if (guess == item) {
                return true;
            }
            if (guess > item) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{1,3,5,7,8,10}, 7));
    }
}
