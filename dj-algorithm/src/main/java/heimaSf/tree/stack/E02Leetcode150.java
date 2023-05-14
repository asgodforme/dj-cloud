package heimaSf.tree.stack;

import java.util.regex.Pattern;

public class E02Leetcode150 {

    private static final Pattern NUMBER_REGX = Pattern.compile("[0-9]+");

    public static int evalRPN1(String[] tokens) {

        ArrayStack<String> stack = new ArrayStack<>(tokens.length);
        for (int i = 0; i < tokens.length; i++) {
            if (NUMBER_REGX.matcher(tokens[i]).matches()) {
                stack.push(tokens[i]);
            } else {
                String second = stack.pop();
                String first = stack.pop();
                int result = 0;
                if ("+".equals(tokens[i])) {
                    result = Integer.parseInt(first) + Integer.parseInt(second);
                } else if ("-".equals(tokens[i])) {
                    result = Integer.parseInt(first) - Integer.parseInt(second);
                } else if ("*".equals(tokens[i])) {
                    result = Integer.parseInt(first) * Integer.parseInt(second);
                } else {
                    result = Integer.parseInt(first) / Integer.parseInt(second);
                }
                stack.push(result + "");
            }
        }
        if (!stack.isEmpty()) {
            return Integer.parseInt(stack.pop());
        }
        return -1;
    }

    /*public static int evalRPN(String[] tokens) {
        ArrayStack<Integer> stack = new ArrayStack<>(tokens.length);
        for(String t : tokens) {
            switch (t) {
                case "+" -> {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a + b);
                }
                case "-" -> {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a - b);
                }
                case "*" -> {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a * b);
                }
                case "/" -> {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a / b);
                }
                default -> {
                    stack.push(Integer.parseInt(t));
                }
            }
        }
        return stack.pop();
    }*/

    public static void main(String[] args) {
        String[] tokens = {"5", "2", "+", "10", "*"};
        System.out.println(evalRPN1(tokens));
    }
}
