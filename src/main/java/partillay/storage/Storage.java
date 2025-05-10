package partillay.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import partillay.exception.PartillayInvalidCommandException;
import partillay.task.Deadline;
import partillay.task.Event;
import partillay.task.Task;
import partillay.task.TaskList;
import partillay.task.ToDo;

/**
 * Represents a storage system for saving and retrieving tasks from a text file.
 */
public class Storage {
    private static final String FILE_PATH = System.getProperty("user.dir") + "/src/data/Partillay.txt";
    private static final String DIR_PATH = System.getProperty("user.dir") + "/src/data/";
    private static ArrayList<Task> tasks;

    /**
     * Initializes the Storage system by ensuring the file exists and loading existing tasks.
     */
    public Storage() {
        checkAndPrepareFile();
        tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    /**
     * Returns the list of tasks currently stored.
     *
     * @return an ArrayList of Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Ensures the storage directory and file exist before accessing them.
     * If they do not exist, they are created.
     */
    public static void checkAndPrepareFile() {
        Path dirPath = Paths.get(DIR_PATH);
        Path filePath = Paths.get(FILE_PATH);
        if (!Files.exists(dirPath)) {
            try {
                Files.createDirectories(dirPath);
                System.out.println("Directory created: " + dirPath);
            } catch (IOException e) {
                System.err.println("Failed to create directory: " + e.getMessage());
            }
        }
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
                System.out.println("File created: " + filePath);
            } catch (IOException e) {
                System.err.println("Failed to create file: " + e.getMessage());
            }
        }
    }

    /**
     * Saves the current list of tasks to the storage file, overwriting any previous data.
     *
     * @param tasks The TaskList containing tasks to be saved.
     */
    public void writeTasksToFile(TaskList tasks) {
        Path path = Paths.get(FILE_PATH);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            System.err.println("Error deleting or creating the file: " + e.getMessage());
        }
        ArrayList<Task> tasksToWrite = tasks.getTasks();
        try (BufferedWriter writer = Files.newBufferedWriter(
                path, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            for (int i = 0; i < tasksToWrite.size(); i++) {
                writer.write(tasksToWrite.get(i).getTxtFormat());
                if (i < tasksToWrite.size() - 1) {
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file, parsing each line into a corresponding Task object.
     * Tasks are stored in the format:
     * - "D | status | description | by" (Deadline)
     * - "E | status | description | from | to" (Event)
     * - "T | status | description" (ToDo)
     */
    public void loadTasksFromFile() {
        Path path = Paths.get(FILE_PATH);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0].trim();

                // Parse based on the type (D, E, or T)
                switch (type) {
                case "D" -> {
                    // Deadline: D | status | description | by
                    String status = parts[1].trim();
                    String description = parts[2].trim();
                    String by = parts[3].trim();
                    tasks.add(new Deadline(description, by, status));
                }
                case "E" -> {
                    // Event: E | status | description | from | to
                    String status = parts[1].trim();
                    String description = parts[2].trim();
                    String from = parts[3].trim();
                    String to = parts[4].trim();
                    tasks.add(new Event(description, from, to, status));
                }
                case "T" -> {
                    // ToDo: T | status | description
                    String status = parts[1].trim();
                    String description = parts[2].trim();
                    tasks.add(new ToDo(description, status));
                }
                default -> throw new PartillayInvalidCommandException("That's not a valid command, bestie!");
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load tasks: " + e.getMessage());
        }

    }
}

