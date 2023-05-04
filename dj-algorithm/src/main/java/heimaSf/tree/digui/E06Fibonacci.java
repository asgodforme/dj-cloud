package heimaSf.tree.digui;

import java.util.Arrays;

/**
 * 记忆法
 */
public class E06Fibonacci {
    public static void main(String[] args) {
        int target = 4;
        int[] cache = new int[target + 1];
        Arrays.fill(cache, -1);
        cache[0] = 0;
        cache[1] = 1;
        System.out.println(f(target, cache));
    }

    public static int f(int target, int[] cache) {
        /*if (target == 0) {
            return 0;
        }
        if (target == 1) {
            return 1;
        }*/
        if (cache[target] != -1) {
            return cache[target];
        }
        int x = f(target - 1, cache);
        int y = f(target - 2, cache);
        int i = x + y;
        cache[target] = i;
        return i;
    }
}
