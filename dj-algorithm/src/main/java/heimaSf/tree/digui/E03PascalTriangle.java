package heimaSf.tree.digui;

public class E03PascalTriangle {

    private static int element(int i, int j) {
        if (j == 0 || i == j) {
            return 1;
        }
        return element(i - 1, j - 1) + element(i - 1, j);
    }

    private static int element(int[][] cache, int i, int j) {
        if (cache[i][j] != 0) {
            return cache[i][j];
        }
        if (j == 0 || i == j) {
            cache[i][j] = 1;
            return 1;
        }
        cache[i][j] = element(i - 1, j - 1) + element(i - 1, j);
        return cache[i][j] ;
    }

    private static void createRow(int[] row, int i) {
        if (i == 0) {
            row[0] = 1;
            return;
        }
        for (int j = i; j > 0 ; j--) {
            row[j] = row[j] + row[j - 1];

        }
    }

    private static void printSpace(int n, int i) {
        int num = (n - 1 - i) * 2;
        for (int j = 0; j < num; j++) {
            System.out.print(" ");
        }
    }

    /**
     * 递归
     * @param n
     */
    private static void print(int n) {
        for (int i = 0; i < n; i++) {
            printSpace(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", element(i, j));
            }
            System.out.println();
        }
    }

    /**
     * 记忆法
     * @param n
     */
    private static void print1(int n) {
        int[][] cache = new int[n][];
        for (int i = 0; i < n; i++) {
            cache[i] = new int[i + 1];
            printSpace(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", element(cache, i, j));
            }
            System.out.println();
        }
    }

    /**
     * 动态规划
     * @param n
     */
    private static void print2(int n) {
        int[] cache = new int[n];
        for (int i = 0; i < n; i++) {
            createRow(cache, i);
            printSpace(n, i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", cache[j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        print2(3);

    }
}
