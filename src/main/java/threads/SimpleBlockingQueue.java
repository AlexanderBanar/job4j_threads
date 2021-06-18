package threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>(new ArrayList<>(3));
    private final Object monitor = this;

    public void offer(T value) {
        synchronized (monitor) {
            if (queue.offer(value)) {
                monitor.notifyAll();
            } else {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public T poll() {
        synchronized (monitor) {
            if (queue.isEmpty()) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            monitor.notifyAll();
            return queue.poll();
        }
    }
}
