package src.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
таска - сделать так, чтобы при вызове run с объектом, который эквивалентен объекту,
который уже выполняется, - метод должен ждать.
нужна - некая мапа с текущими вызовами.
 */
public class EqualityLockService implements Service {
    private final Service service;
    private Map<Object, ReentrantLock> map = new ConcurrentHashMap<>();
    private final Object mapGuardian = new Object();

    public EqualityLockService(Service service) {
        this.service = service;
    }

    // bullshit
    @Override
    public void run(Object o) {
        ReentrantLock lock = getLock(o);
        lock.lock();
        try {
            service.run(o);
        } finally {
            lock.unlock();
            synchronized (mapGuardian) {
                if (lock.getQueueLength() == 0) {
                    map.remove(o);
                }
            }
        }
    }

    private ReentrantLock getLock(Object o) {
        synchronized (mapGuardian) {
            return map.putIfAbsent(o, new ReentrantLock());
        }
    }
}
