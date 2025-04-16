package pluto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage class that handles the loading
 * and saving of tasks for recurring use
 */
public class Storage {
    private String filePath;

    /**
     * Creates a new Storage
     * @param filePath a String which specifies the file that stores
     *                 the task list
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file
     * @return a List of Tasks loaded from the file
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            Message.newListMessage();
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Task.fromFileFormat(line));
            }
        } catch (IOException e) {
            Message.showErrorMessage("Error loading tasks:" + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the updated task list to the storage file
     * @param tasks the updated List of Tasks
     */
    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            Message.showErrorMessage("Error saving tasks: " + e.getMessage());
        }
    }
}
