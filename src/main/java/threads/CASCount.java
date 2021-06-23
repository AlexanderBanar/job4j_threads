package threads;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        Integer temp;
        do {
            temp = count.get();
            if (temp == Integer.MAX_VALUE) {
                throw new UnsupportedOperationException("Count is not implemented as already MAX valued");
            }
        } while (!count.compareAndSet(temp, temp + 1));
    }

    public int get() {
        if (count.get() == 0) {
            throw new UnsupportedOperationException("Count has not yet been implemented, 0 value");
        }
        return count.get();
    }
}
