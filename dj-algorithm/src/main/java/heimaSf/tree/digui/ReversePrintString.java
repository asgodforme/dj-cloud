package heimaSf.tree.digui;

public class ReversePrintString {

    public static void main(String[] args) {
        recursion(0, "abcd");

    }


    public static void recursion(int n, String target) {
        if (n == target.length()) {
            return;
        }
        recursion(n + 1, target);
        System.out.println(target.charAt(n));
    }
}
