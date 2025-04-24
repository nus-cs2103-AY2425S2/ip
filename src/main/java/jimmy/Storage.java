package jimmy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import jimmy.tasks.Deadline;
import jimmy.tasks.Event;
import jimmy.tasks.Task;
import jimmy.tasks.Todo;

/**
 * Handles the loading and saving of tasks to a file for persistent storage.
 * Ensures that the file and its directories exist, and supports reading from
 * and writing to the file in a specific format.
 */
public class Storage {
    private static final String ERROR_LOADING_FILE = "Error loading tasks from file.";
    private static final String ERROR_SAVING_FILE = "Error saving tasks to file.";
    private static final String ERROR_INVALID_DATE_FORMAT = "Error: Invalid date format in file.";
    private static final String FILE_DELIMITER = " \\| ";
    private final Path filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     * Ensures that the file and its parent directories exist.
     *
     * @param filePath the path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";
        this.filePath = Paths.get(filePath);
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";
        ensureFileExists();
    }

    /**
     * Ensures the file and its parent directories exist.
     * Creates them if they do not exist.
     */
    private void ensureFileExists() {
        try {
            Path directory = filePath.getParent();
            if (directory != null && !Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            assert Files.exists(filePath) : "File creation failed";
        } catch (IOException e) {
            System.out.println("Error ensuring file exists: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file into an {@code ArrayList}.
     * Parses each line of the file to recreate the corresponding {@code Task} objects.
     *
     * @return a list of tasks loaded from the file.
     * @throws JimmyException if an I/O error occurs during file reading.
     */
    public ArrayList<Task> load() throws JimmyException {
        assert Files.exists(filePath) : "Task file should exist before loading";

        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new JimmyException(ERROR_LOADING_FILE);
        }

        return tasks;
    }

    /**
     * Parses a line from the file and converts it into a {@code Task} object.
     * Handles different types of tasks based on their type identifier.
     *
     * @param line the line read from the file.
     * @return the corresponding {@code Task} object, or {@code null} if the line is invalid.
     * @throws JimmyException
     */
    private Task parseTask(String line) throws JimmyException {
        String[] parts = line.split(FILE_DELIMITER);
        if (parts.length < 3) {
            return null; // Skip invalid lines
        }

        Task task;
        try {
            switch (parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = parseDeadline(parts);
                break;
            case "E":
                task = parseEvent(parts);
                break;
            default:
                return null;
            }
            if (parts[1].equals("1")) {
                task.mark();
            }
            return task;
        } catch (DateTimeParseException e) {
            System.out.println(ERROR_INVALID_DATE_FORMAT);
            return null;
        }
    }

    /**
     * Parses a deadline task from file data.
     *
     * @param parts the parts of the task data split by the delimiter.
     * @return a {@code Deadline} task.
     * @throws JimmyException
     */
    private Task parseDeadline(String[] parts) throws JimmyException {
        return (parts.length >= 4) ? new Deadline(parts[2], parts[3]) : null;
    }

    /**
     * Parses an event task from file data.
     *
     * @param parts the parts of the task data split by the delimiter.
     * @return an {@code Event} task.
     * @throws JimmyException
     */
    private Task parseEvent(String[] parts) throws JimmyException {
        return (parts.length >= 5) ? new Event(parts[2], parts[3], parts[4]) : null;
    }

    /**
     * Saves the provided list of tasks to the file.
     * Each task is converted to a file-friendly format before saving.
     *
     * @param tasks the list of tasks to be saved.
     * @throws JimmyException if an I/O error occurs during file writing.
     */
    public void save(ArrayList<Task> tasks) throws JimmyException {
        assert tasks != null : "Task list should not be null";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString()))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new JimmyException(ERROR_SAVING_FILE);
        }
    }
}
