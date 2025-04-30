package yuki;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.util.Scanner;

import yuki.task.Deadline;
import yuki.task.Task;
import yuki.task.Event;
import yuki.task.Todo;

/**
 * Represents a storage object that handles saving and loading tasks to and from a file.
 */
public class Storage {
    private static String filePath = "./data/yuki.txt";
    private static final TaskList<Task> storageTasks = new TaskList<>();
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Saves the tasks to a file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void save(TaskList<Task> tasks) {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).toFileString() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("An error occurred while saving tasks to file.");
        }
    }

    /**
     * Loads the tasks from a file.
     *
     * @return The list of tasks loaded from the file.
     * @throws YukiException If the file is not found or if the task type is invalid.
     */
    public static TaskList<Task> load() throws YukiException {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                switch (parts[0]) {
                case "T":
                    storageTasks.add(new Todo(parts[1], parts[2]));
                    break;
                case "D":
                    storageTasks.add(new Deadline(parts[1], parts[2], parts[3]));
                    break;
                case "E":
                    storageTasks.add(new Event(parts[1], parts[2], parts[3], parts[4]));
                    break;
                default:
                    throw new YukiException("Invalid task type");
                }
            }
        } catch (FileNotFoundException e) {
            throw new YukiException("File not found");
        }
        return storageTasks;
    }
}
