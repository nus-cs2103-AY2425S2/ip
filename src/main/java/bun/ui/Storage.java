package bun.ui;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    /**
     * Constructs a new instance of `Storage` with the specified parameters.
     *
     * @param filePath Filepath to store the task list locally.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Stores the task list into filePath
     *
     * @param tasks Task List to be stored
     */
    public void save(TaskList tasks) {
        Path dataDir = Path.of("data");
        String content = tasks.toStoredContent();

        try {
            Files.createDirectories(dataDir);

            Files.writeString(
                    Path.of(this.filePath),
                    content + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

        } catch (IOException e) {
            System.err.println("Failed to write to tasks.txt: " + e.getMessage());
        }
    }

    /**
     * Returns an array list with tasks stored locally at filePath/
     *
     * @return Array list with existing tasks.
     * @throws BunException If tasks cannot be loaded properly.
     */
    public ArrayList<Task> load() throws BunException {
        Path filePath = Path.of(this.filePath);
        ArrayList<Task> returnedTasks = new ArrayList<>();
        try {
            // Return empty array if file does not exist
            if (!Files.exists(filePath)) {
                System.out.println("File does not exist: " + filePath);
                return returnedTasks;
            }

            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                Task task = Task.stringToTask(line);
                if (task != null) {
                    returnedTasks.add(task);
                }
            }
            return returnedTasks;
        } catch (IOException e) {
            throw new BunException("Error reading the stored task list.");
        }
    }
}
