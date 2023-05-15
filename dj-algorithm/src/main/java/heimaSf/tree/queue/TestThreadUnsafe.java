package heimaSf.tree.queue;

import java.util.Arrays;

public class TestThreadUnsafe {
    private final String[] array = new String[10];
    private int tail = 0;

    public void offer(String e) {
        array[tail] = e;
        tail++;
    }


    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public static void main(String[] args) throws InterruptedException {
        TestThreadUnsafe queue = new TestThreadUnsafe();
        new Thread(() -> queue.offer("e1"), "t1").start();
        new Thread(() -> queue.offer("e2"), "t2").start();

        Thread.sleep(1000);
        System.out.println(queue);
    }
}
