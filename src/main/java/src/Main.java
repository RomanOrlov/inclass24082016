package src;

/**
 * Created by user6 on 27.08.2016.
 */
public class Main {
    Semaphore semaphore = new Semaphore(5);

    public void doHardJob() {
        System.out.println("DOING JOB in "+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        semaphore.enter();
        try {
            doHardJob();
        } finally {
            semaphore.exit();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        for (int i=0;i<100;i++) {
            new Thread(main::run, String.valueOf(i)).start();
        }
    }
}
