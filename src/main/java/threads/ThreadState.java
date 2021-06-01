package threads;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread zero = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        zero.start();
        first.start();
        Thread.sleep(1000);
        if (zero.getState() == Thread.State.TERMINATED
                && first.getState() == Thread.State.TERMINATED) {
            System.out.println();
            System.out.printf("This is Thread: %s\r\n", Thread.currentThread().getName());
            System.out.println("The zero thread and the first threads are terminated");
        }
    }
}
