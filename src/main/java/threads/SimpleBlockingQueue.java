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
    private int currSize = 0;
    private int maxSize;

    public SimpleBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (monitor) {
            while (currSize > maxSize) {
                monitor.wait();
            }
            queue.offer(value);
            monitor.notifyAll();
            currSize++;
        }
    }

    public T poll() throws InterruptedException {
        synchronized (monitor) {
            while (currSize < 1) {
                monitor.wait();
            }
            monitor.notifyAll();
            currSize--;
            return queue.poll();
        }
    }
}
