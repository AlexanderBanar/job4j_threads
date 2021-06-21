package threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final Object monitor = this;
    private int maxSize;

    public SimpleBlockingQueue() {
        this.maxSize = Integer.MAX_VALUE;
    }

    public SimpleBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (monitor) {
            while (queue.size() > maxSize) {
                monitor.wait();
            }
            queue.offer(value);
            monitor.notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (monitor) {
            while (queue.size() < 1) {
                monitor.wait();
            }
            monitor.notifyAll();
            return queue.poll();
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
