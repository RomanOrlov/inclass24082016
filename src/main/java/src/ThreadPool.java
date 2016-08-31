package src;

/**
 * Created by user6 on 31.08.2016.
 */

public interface ThreadPool {
    void start();

    void execute(Runnable runnable);
}
