package ferb.filehandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import ferb.task.Task;

/**
 * Represents a file handler that reads and writes to a file that stores the data of task list of Ferb.
 */
public class FerbFileHandler {
    private String filePath;
    private File file;

    public FerbFileHandler(String filepath) {
        this.filePath = filepath;
        this.file = new File(filepath);
    }

    /**
     * Reads the content of the file that stores the previous task list of Ferb.
     *
     * @return The content of the file.
     */
    public String readContent(){
        String content = "";
        try {
            File parentDir = file.getParentFile();
            parentDir.mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            } else if (file.exists()) {
                content = new String(Files.readAllBytes(Paths.get(filePath)));
            }
        } catch (IOException e) {
            System.err.println("An error occured" + e);
        } finally {
            return content;
        }
    }

    /**
     * Writes the content of the task list to the file.
     *
     * @param tasks The list of tasks to be written to the file.
     */
    public void writeContent(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/Ferb.txt"))) {
            for (Task task : tasks) {
                String line = task.toSave();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
