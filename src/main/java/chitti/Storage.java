package chitti;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Handles loading and saving tasks to and from a file.
 * This class manages reading tasks from a file on startup and saving tasks to the file when the application shuts down.
 */
public class Storage {
    private final String filePath;
    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Loads the tasks from the file and returns them as an ArrayList.
     * If the file does not exist, an empty list is returned.
     *
     * @return An ArrayList of tasks loaded from the file.
     */
    ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return taskList;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                taskList.add(Task.fromFileFormat(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return taskList;
    }
    /**
     * Saves the tasks in the given TaskList to the file.
     * This method writes each task's file format representation to the file.
     *
     * @param taskList The TaskList containing the tasks to be saved.
     */
    void saveTasksToFile(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList.getTasks()) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
