package hamiltonianguy.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import hamiltonianguy.tasks.DeadlineTask;
import hamiltonianguy.tasks.ToDoTask;
import hamiltonianguy.tasks.Task;
import hamiltonianguy.tasks.EventTask;

/**
 * Handles saving and loading tasks to and from a file.
 */

public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with a specified file path.
     *
     * @param filePath The path of the file to store tasks.
     */

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from the file.
     */

    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No existing save file found. Starting fresh.");
            return tasks;  // Return an empty list
        }
        

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                } else {
                    System.out.println("Warning: Skipping corrupted entry -> " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the save file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the list of tasks to the storage file.
     *
     * @param tasks The list of tasks to save.
     */

    public void save(List<Task> tasks) {
        File file = new File(filePath);
        assert file.getParentFile() != null : "Storage directory is missing!";

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }



    /**
     * Parses a task from the file format.
     */

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null; // Corrupted entry
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                return new ToDoTask(description, isDone);
            case "D":
                return new DeadlineTask(description, LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")), isDone);
            case "E":
                return new EventTask(description,
                        LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                        LocalDateTime.parse(parts[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                        isDone);
            default:
                return null; 
        }
    }
}


