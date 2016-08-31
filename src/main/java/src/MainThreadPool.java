package src;

/**
 * Created by user6 on 31.08.2016.
 */
public class MainThreadPool {
    public static void main(String[] args) {
        FixedThreadPool threadPool = new FixedThreadPool(5);
        for (int i = 0;i<50;i++) {
            final int ii = i;
            threadPool.execute(()->{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task "+ii);
            });
        }
        threadPool.start();
    }
}
