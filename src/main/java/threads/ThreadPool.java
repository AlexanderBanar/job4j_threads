package threads;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                tasks.poll().run();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
            );
            thread.start();
            threads.add(thread);
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public static class Job implements Runnable {
        @Override
        public void run() {
            int count = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(count++);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Job job = new Job();
        Job job2 = new Job();
        ThreadPool threadPool = new ThreadPool();
        threadPool.work(job);
        threadPool.work(job2);
        Thread.sleep(2000);
        threadPool.shutdown();
    }
}
