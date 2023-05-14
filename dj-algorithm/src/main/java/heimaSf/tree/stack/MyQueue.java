package heimaSf.tree.stack;

public class MyQueue {

    private ArrayStack<Integer> s1 = new ArrayStack<>(1024);
    private ArrayStack<Integer> s2 = new ArrayStack<>(1024);

    public MyQueue() {

    }

    public void push(int x) {
        s2.push(x);
    }

    public int pop() {
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        return s1.pop();
    }

    public int peek() {
        if (s1.isEmpty()) {
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }
        return s1.peek();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
