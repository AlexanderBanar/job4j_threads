package threads;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {
//    @Test
//    public void whenSending4ElementsAndBlockingProducer() throws InterruptedException {
//        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
//        AtomicInteger result = new AtomicInteger();
//        AtomicInteger valueOfFour = new AtomicInteger();
//        Thread producer = new Thread(
//                () -> {
//                    queue.offer(1);
//                    queue.offer(2);
//                    queue.offer(3);
//                    queue.offer(4);
//                }
//        );
//        Thread consumer = new Thread(
//                () -> {
//                    result.addAndGet(queue.poll());
//                    result.addAndGet(queue.poll());
//                    result.addAndGet(queue.poll());
//                    valueOfFour.addAndGet(queue.poll());
//                }
//        );
//        producer.start();
//        consumer.start();
//        producer.join();
//        consumer.join();
//        assertThat(result.get(), is(6));
//        assertThat(valueOfFour.get(), is(4));
//    }

}