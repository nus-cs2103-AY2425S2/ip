package nyanko.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import nyanko.task.InvalidTaskFormatException;
import nyanko.task.Task;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a {@code Storage} instance.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * If the file does not exist, it creates a new empty file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an error occurs while reading the file.
     * @throws InvalidTaskFormatException If a task format is invalid.
     */
    public ArrayList<Task> load() throws IOException, InvalidTaskFormatException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // If file doesn't exist, create necessary directories and file
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Create parent directory if needed
            file.createNewFile();
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromSaveFormat(line);
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Saves the task list to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        }
    }
}
