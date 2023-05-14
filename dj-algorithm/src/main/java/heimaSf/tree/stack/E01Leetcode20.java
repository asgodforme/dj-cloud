package heimaSf.tree.stack;

public class E01Leetcode20 {

    public static boolean isValid(String s) {
        ArrayStack<Character> stack = new ArrayStack<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c =='[') {
                stack.push(']');
            } else {
                if (!stack.isEmpty() && c == stack.peek()) {
                   stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "([]}";
        System.out.println(isValid(s));
    }
}
