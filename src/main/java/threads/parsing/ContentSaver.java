package threads.parsing;

import java.io.*;

public class ContentSaver {
    private final File file;

    public ContentSaver(File file) {
        this.file = file;
    }

    public void saveContent(String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < content.length(); i++) {
                bw.write(content.charAt(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
