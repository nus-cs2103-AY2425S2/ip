package yapper.datastorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import yapper.parser.Parser;
import yapper.tasktypes.Task;
import yapper.tasktypes.TaskList;

/**
 * Handles loading and saving of tasks from a data file.
 */
public class DataStorage {
    private File file;

    /**
     * Constructs a DataStorage instance with the given file path.
     *
     * @param filePath The path to the file used for storing task data.
     */
    public DataStorage(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path must not be null or empty";
        this.file = new File(filePath);
    }

    /**
     * Loads tasks from the data file.
     * If the file does not exist, it is created, and an empty task list is returned.
     *
     * @return A TaskList containing the tasks loaded from the file.
     */
    public TaskList loadData() {
        TaskList taskList = new TaskList();
        try {
            // Ensure the parent directory exists
            File parentDir = this.file.getParentFile();
            if (!parentDir.exists() && !parentDir.mkdirs()) {
                System.out.println("Failed to create directory: " + parentDir.getAbsolutePath());
            }

            // Create file if it does not exist
            assert this.file != null : "File object is not initialized properly";
            if (!this.file.exists() && !this.file.createNewFile()) {
                System.out.println("Error: Failed to create file.");
                return taskList;
            }
            if (!this.file.exists() && !this.file.createNewFile()) {
                System.out.println("Error: Failed to create file.");
                return taskList;
            }

            // Read the file
            try (Scanner scanner = new Scanner(this.file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    if (!line.isEmpty()) {
                        Parser.executeCommand(line, taskList);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An IO error occurred: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Saves the current list of tasks to the data file.
     * If the parent directory does not exist, it is created before writing to the file.
     *
     * @param taskList The list of tasks to be saved to the file.
     */
    public void saveData(TaskList taskList) {
        assert this.file != null && this.file.canWrite() : "File must be writable before saving data";
        try (FileWriter fileWriter = new FileWriter(this.file)) {
            assert taskList != null : "TaskList cannot be null";
            ArrayList<Task> tasks = taskList.getList();
            assert tasks != null : "TaskList cannot be null";

            // Ensure the parent directory exists
            File parentDir = this.file.getParentFile();
            if (!parentDir.exists() && !parentDir.mkdirs()) {
                System.out.println("Failed to create directory: " + parentDir.getAbsolutePath());
            }

            // Write to file
            for (Task task : tasks) {
                fileWriter.write(task.getUserInput() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
