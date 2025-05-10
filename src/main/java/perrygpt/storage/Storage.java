package perrygpt.storage;

import perrygpt.tasks.Deadline;
import perrygpt.tasks.Event;
import perrygpt.tasks.Todo;
import perrygpt.exceptions.PerryGPTException;
import perrygpt.tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Storage} class is responsible for loading tasks from a file
 * and saving tasks to a file. It ensures that the information on tasks are
 * persisted between sessions of the application.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path should not be null or empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file.
     * If the file does not exist, it creates the file and the necessary directories.
     *
     * @return An {@link ArrayList} of tasks loaded from the file.
     * @throws PerryGPTException If the file contains invalid or corrupted data.
     * @throws IOException   If there is an error creating the file or directory.
     */
    public ArrayList<Task> load() throws PerryGPTException, IOException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                if (!parentDir.mkdirs()) {
                    throw new IOException("Failed to create directory: " + parentDir.getAbsolutePath());
                }
            }
            if (!file.createNewFile()) {
                throw new IOException("Failed to create file: " + file.getAbsolutePath());
            }
        } else {
            try (Scanner s = new Scanner(file)) {
                while (s.hasNextLine()) {
                    tasks.add(parseTask(s.nextLine()));
                }
            } catch (Exception e) {
                throw new PerryGPTException("Error parsing tasks from file: " + e.getMessage());
            }
        }
        return tasks;
    }

    /**
     * Parses a line from the file into a {@link Task}.
     *
     * @param line A line from the file representing a task.
     * @return A {@link Task} object corresponding to the line.
     * @throws PerryGPTException If the line format is invalid or the task type is unknown.
     */
    private Task parseTask(String line) throws PerryGPTException {
        assert line != null && !line.isEmpty() : "Task line should not be null or empty string";
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            throw new PerryGPTException("Corrupted data file! Unable to parse task.");
        }

        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");

        switch (taskType) {
            case "T":
                return new Todo(parts[2], isDone);
            case "D":
                if (parts.length < 4) {
                    throw new PerryGPTException("Invalid deadline format in storage file.");
                }
                return new Deadline(parts[2], parts[3], isDone);
            case "E":
                if (parts.length < 5) {
                    throw new PerryGPTException("Invalid event format in storage file.");
                }
                return new Event(parts[2], parts[3], parts[4], isDone);
            default:
                throw new PerryGPTException("Unknown task type in storage file.");
        }
    }

    /**
     * Saves all tasks to the file, overwriting any existing content.
     *
     * @param tasks An {@link ArrayList} of tasks to be saved to the file.
     * @throws IOException If there is an error writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) { // Overwrite the file
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        }
    }
}