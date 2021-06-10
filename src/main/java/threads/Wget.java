package threads;

import java.io.*;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final String file;
    private final int speed;

    public Wget(String url, String file, int speed) {
        this.url = url;
        this.file = file;
        this.speed = speed;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        download();
        long timeDelta = System.currentTimeMillis() - startTime;
        long speedToLong = speed * 1000L;
        if (timeDelta < speedToLong) {
            try {
                Thread.sleep(speedToLong - timeDelta);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void download() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        String file = args[1];
        int speed = Integer.parseInt(args[2]);
        if (args.length == 3) {
            Thread wget = new Thread(new Wget(url, file, speed));
            wget.start();
            wget.join();
        } else {
            System.out.println("Error! Program parameters are incorrect or missing!");
        }
    }
}
