import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                queue.put("aaa");
                queue.put("bbb");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        Thread.sleep(2000);

        new Thread(() -> {
            try {
                System.out.println(queue.take());
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
