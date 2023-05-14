package heimaSf.tree.stack;

import heimaSf.tree.queue.ArrayQueue3;

public class E05Leetcode225 {
    ArrayQueue3<Integer> queue = new ArrayQueue3(100);
    int size;

    public void push(int x) {
        queue.offer(x);
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
        size++;
    }

    public int pop() {
        size--;
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
