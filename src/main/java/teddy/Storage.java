package teddy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        ensureDataDirectoryExists();
    }

    // Ensures that the "data" directory exists before writing to teddy.txt
    private void ensureDataDirectoryExists() {
        File file = new File(filePath);
        File directory = file.getParentFile();

        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }
    }

    // Writes the task input by user into teddy.txt
    public void writeToFile(String input) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(input);
        fw.write("\n");
        fw.close();
    }
}
