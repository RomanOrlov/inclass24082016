package src;

/**
 * Created by user6 on 27.08.2016.
 */
public class Semaphore {
    private final int maxThreadCount;
    private int currentThreads;

    public Semaphore(int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
    }

    public synchronized void enter() {
        while (currentThreads == maxThreadCount) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        currentThreads++;
    }

    public synchronized void exit() {
        currentThreads--;
        notify();
    }
}
