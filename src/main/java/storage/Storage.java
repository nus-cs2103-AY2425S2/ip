package storage;

import task.Task;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {

    /**
     * Saves the list of tasks to the specified file path.
     *
     * @param filePath The file path where tasks should be saved.
     * @param tasks The list of tasks to save.
     */
    public static void saveTasks(String filePath, List<Task> tasks) {
        try {
            File file = new File(filePath);

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : tasks) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Saving tasks error: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the specified file path.
     *
     * @param filePath The file path from which tasks should be loaded.
     * @return A list of tasks loaded from the file.
     */
    public static ArrayList<Task> loadTasks(String filePath) {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }

        try {
            Scanner scanner = new Scanner(file);

            if (!scanner.hasNextLine()) {
                scanner.close();
                return tasks;
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                try {
                    tasks.add(Task.fromSaveFormat(line));
                } catch (Exception e) {
                    System.out.println("Task entry error" );
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Loading tasks error: " + e.getMessage());
        }
        return tasks;
    }
}