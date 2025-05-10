package storage;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private File file;

    /**
     * Creates a Storage object and ensures the file exists.
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        verifyFileExistence();
    }

    /**
     * Ensures that the file and its parent directories exist.
     */
    private void verifyFileExistence() {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                boolean isFileCreated = file.createNewFile();
                if (!isFileCreated) {
                    System.out.println("Warning: File already exists or could not be created.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file.
     * @return A list of tasks.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                } else {
                    System.out.println("Skipped corrupted line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the current task list to the file.
     * @param tasks The list of tasks to save.
     */
    public void save(ArrayList<Task> tasks) {
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
     * Parses a task from a formatted string.
     * @param line The line from the storage file.
     * @return A Task object or null if parsing fails.
     */
    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (type) {
                case "T":
                    return new Todo(description, isDone);
                case "D":
                    return new Deadline(description, Task.parseDate(parts[3]).format(Task.INPUT_FORMAT), isDone);
                case "E":
                    return new Event(description,
                            Task.parseDate(parts[3]).format(Task.INPUT_FORMAT),
                            Task.parseDate(parts[4]).format(Task.INPUT_FORMAT),
                            isDone);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
