package threadtest.deamonThread;

public class TestDeamon {
    static class MyThread extends Thread {
        private boolean stop = false;
        @Override
        public void run() {
            while (!stop) {
//                System.out.println("t1 is executing");
//                try {
//                    java.lang.Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程被中断");
                    Thread.interrupted();
                    System.out.println(Thread.currentThread().getName() + this.isInterrupted());
                }
            }
        }

        public void stopT() {
            this.stop = true;
        }
    };
    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
//        t1.setDaemon(true);
        t1.start();
//        t1.stopT();
//        t1.join();

        Thread.sleep(5000);
        System.out.println(t1.isInterrupted());
        t1.interrupt();
        System.out.println(t1.isInterrupted());
        Thread.sleep(5000);
        System.out.println("main exit");
    }
}
