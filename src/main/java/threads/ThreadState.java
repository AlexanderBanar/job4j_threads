package threads;

public class ThreadState {
    public static void main(String[] args) {
        Thread zero = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        zero.start();
        first.start();
        while (zero.getState() != Thread.State.TERMINATED
                || first.getState() != Thread.State.TERMINATED) {
            System.out.println("the branch threads are still running...");
        }
        System.out.println();
        System.out.printf("This is Thread: %s\r\n", Thread.currentThread().getName());
        System.out.println("The zero thread and the first threads are terminated");
    }
}
