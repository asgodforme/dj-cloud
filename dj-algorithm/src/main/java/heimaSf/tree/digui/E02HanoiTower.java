package heimaSf.tree.digui;

import java.util.LinkedList;

public class E02HanoiTower {
    static LinkedList<Integer> a = new LinkedList<>();
    static LinkedList<Integer> b = new LinkedList<>();
    static LinkedList<Integer> c = new LinkedList<>();

    static void init(int n) {
        for (int i = n; i >= 1; i--) {
            a.addLast(i);
        }
    }

    /**
     *
     * @param n
     * @param a 原始
     * @param b 借助
     * @param c 目标
     */
    static void move(int n, LinkedList<Integer> a, LinkedList<Integer> b, LinkedList<Integer> c) {
        if (n == 0) {
            return;
        }
        move(n - 1, a, c, b);
        c.addLast(a.removeLast());
        print();
        move(n - 1, b, a, c);
    }

    public static void main(String[] args) {
        init(64);
        move(64, a, b, c);
    }

    private static void print() {
        System.out.println("----------------------");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
