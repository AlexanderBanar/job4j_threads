package threads;

public class Wget {
    public static void main(String[] args) {
        System.out.print("\rLoading : " + "0%");
        Thread first = new Thread(
                () -> {
                    for (int i = 1; i <= 100; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print("\rLoading : " + i + "%");
                    }
                }
        );
        first.start();
    }
}
