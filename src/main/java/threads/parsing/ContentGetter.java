package threads.parsing;

import java.io.*;
import java.util.function.Predicate;

public class ContentGetter {
    private final File file;

    public ContentGetter(File file) {
        this.file = file;
    }

    public String getContent(Predicate<Character> filter) {
        StringBuffer output = new StringBuffer();
        int data;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((data = br.read()) > 0) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
