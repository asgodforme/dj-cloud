package heimaSf.tree.binarySearch;


import org.junit.Assert;
import org.junit.Test;

public class BinarySearch {

    /**
     * 基础版
     * @param a
     * @param target
     * @return
     */
    public static int binarySearchBasic1(int[] a, int target) {
        int i = 0, j = a.length -1;

        /**
         * 为什么是i<=j, 而不是i< j?
         * i==j 意味者i,j他们指向的元素也会参与比较
         * i<j 只意味着m指向的元素参与比较
         */
        while (i <= j) {
            /**
             *  java中的数字第一位是符号位，当较大的数字想加时，可能算出来负数。
             *  使用位运算能避免这个问题，而且能够更加通用移植。
             */
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m  + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    /**
     * 改进版
     * @param a
     * @param target
     * @return
     */
    public static int binarySearchBasic2(int[] a, int target) {
        int i = 0, j = a.length;

        /**
         * 为什么是i<=j, 而不是i< j?
         * i==j 意味者i,j他们指向的元素也会参与比较
         * i<j 只意味着m指向的元素参与比较
         */
        while (i < j) {
            /**
             *  java中的数字第一位是符号位，当较大的数字想加时，可能算出来负数。
             *  使用位运算能避免这个问题，而且能够更加通用移植。
             */
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m;
            } else if (a[m] < target) {
                i = m  + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    /**
     * 平衡版
     * @param a
     * @param target
     * @return
     */
    public static int binarySearchBasic(int[] a, int target) {
        int i = 0, j = a.length;

        /**
         * 为什么是i<=j, 而不是i< j?
         * i==j 意味者i,j他们指向的元素也会参与比较
         * i<j 只意味着m指向的元素参与比较
         */
        while (1 < j - i) {
            /**
             *  java中的数字第一位是符号位，当较大的数字想加时，可能算出来负数。
             *  使用位运算能避免这个问题，而且能够更加通用移植。
             */
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m;
            } else {
                i = m;
            }
        }

        if (a[i] == target) {
            return i;
        } else {
            return -1;
        }
    }


    @Test
    public void test1() {
        int [] a = {7, 13, 21, 30, 38, 44, 52, 53};
        Assert.assertEquals(0, binarySearchBasic(a, 7));
        Assert.assertEquals(1, binarySearchBasic(a, 13));
        Assert.assertEquals(2, binarySearchBasic(a, 21));
        Assert.assertEquals(3, binarySearchBasic(a, 30));
        Assert.assertEquals(4, binarySearchBasic(a, 38));
        Assert.assertEquals(5, binarySearchBasic(a, 44));
        Assert.assertEquals(6, binarySearchBasic(a, 52));
        Assert.assertEquals(7, binarySearchBasic(a, 53));
    }

    @Test
    public void test2() {
        int [] a = {7, 13, 21, 30, 38, 44, 52, 53};
        Assert.assertEquals(-1, binarySearchBasic(a, 0));
        Assert.assertEquals(-1, binarySearchBasic(a, 15));
        Assert.assertEquals(-1, binarySearchBasic(a, 60));
    }


    /**
     * ≥target的最靠左索引
     * @param a
     * @param target
     * @return
     */
    public static int binarySearchLeftMost(int[] a, int target) {
        int i = 0, j = a.length -1;
        int candidate = -1;
        /**
         * 为什么是i<=j, 而不是i< j?
         * i==j 意味者i,j他们指向的元素也会参与比较
         * i<j 只意味着m指向的元素参与比较
         */
        while (i <= j) {
            /**
             *  java中的数字第一位是符号位，当较大的数字想加时，可能算出来负数。
             *  使用位运算能避免这个问题，而且能够更加通用移植。
             */
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m  + 1;
            } else {
                candidate = m;
                j = m - 1;
            }
        }
        return candidate;
    }

    public static int binarySearchLeftMost1(int[] a, int target) {
        int i = 0, j = a.length -1;
        /**
         * 为什么是i<=j, 而不是i< j?
         * i==j 意味者i,j他们指向的元素也会参与比较
         * i<j 只意味着m指向的元素参与比较
         */
        while (i <= j) {
            /**
             *  java中的数字第一位是符号位，当较大的数字想加时，可能算出来负数。
             *  使用位运算能避免这个问题，而且能够更加通用移植。
             */
            int m = (i + j) >>> 1;
            if (target <= a[m]) {
                j = m - 1;
            } else {
                i = m  + 1;
            }
        }
        return i;
    }


    public static int binarySearchRightMost(int[] a, int target) {
        int i = 0, j = a.length -1;
        int candidate = -1;
        /**
         * 为什么是i<=j, 而不是i< j?
         * i==j 意味者i,j他们指向的元素也会参与比较
         * i<j 只意味着m指向的元素参与比较
         */
        while (i <= j) {
            /**
             *  java中的数字第一位是符号位，当较大的数字想加时，可能算出来负数。
             *  使用位运算能避免这个问题，而且能够更加通用移植。
             */
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m  + 1;
            } else {
                candidate = m;
                i = m + 1;
            }
        }
        return candidate;
    }

    /**
     * 小于等于target最靠右的索引
     * @param a
     * @param target
     * @return
     */
    public static int binarySearchRightMost1(int[] a, int target) {
        int i = 0, j = a.length -1;
        /**
         * 为什么是i<=j, 而不是i< j?
         * i==j 意味者i,j他们指向的元素也会参与比较
         * i<j 只意味着m指向的元素参与比较
         */
        while (i <= j) {
            /**
             *  java中的数字第一位是符号位，当较大的数字想加时，可能算出来负数。
             *  使用位运算能避免这个问题，而且能够更加通用移植。
             */
            int m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else {
                i = m  + 1;
            }
        }
        return i - 1;
    }

    @Test
    public void test3() {
        int [] a = {1, 3, 4, 4, 4, 7, 8, 9};
        Assert.assertEquals(0, binarySearchRightMost1(a, 1));
        Assert.assertEquals(1, binarySearchRightMost1(a, 3));
        Assert.assertEquals(4, binarySearchRightMost1(a, 4));
        Assert.assertEquals(5, binarySearchRightMost1(a, 7));
        Assert.assertEquals(6, binarySearchRightMost1(a, 8));
        Assert.assertEquals(7, binarySearchRightMost1(a, 9));

        Assert.assertEquals(-1, binarySearchRightMost1(a, 0));
        Assert.assertEquals(7, binarySearchRightMost1(a, 15));
        Assert.assertEquals(7, binarySearchRightMost1(a, 60));
    }
}
