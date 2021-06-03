package threads;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        int count = 0;
        String[] symbols = new String[3];
        symbols[0] = "(  ";
        symbols[1] = "  )";
        symbols[2] = " _ ";
        int symbolCount = 0;
        while (!Thread.currentThread().isInterrupted()
        && count < 101) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("\rLoading... %s, progress: %d%%", symbols[symbolCount++], count++);
            if (symbolCount == 3) {
                symbolCount = 0;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(8000);
        progress.interrupt();
    }
}
