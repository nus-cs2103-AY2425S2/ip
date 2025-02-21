package avocado.storage;

import avocado.task.Deadline;
import avocado.task.Event;
import avocado.task.Task;
import avocado.task.Todo;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of tasks in the hard disk.
 */
public class Storage {
    private static final String FILE_PATH = "./data/tasks.txt";

    private String filePath;

    /**
     * Constructs a Storage object with the default file path.
     */
    public Storage() {
        this.filePath = FILE_PATH;
    }

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path to store tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks The list of tasks to save.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // Ensure directory exists
            try (FileWriter writer = new FileWriter(file)) {
                for (Task task : tasks) {
                    writer.write(taskToFileFormat(task) + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks from the file.
     *
     * @return The list of tasks loaded from the file.
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks; // Return empty list if no previous data
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found.");
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the tags to the file.
     *
     * @param tags The list of tags to save.
     */
    public static void saveTags(ArrayList<String> tags) {
        try {
            File file = new File("./data/tags.txt");
            file.getParentFile().mkdirs(); // Ensure directory exists
            try (FileWriter writer = new FileWriter(file)) {
                for (String tag : tags) {
                    writer.write(tag + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tags to file: " + e.getMessage());
        }
    }

    /**
     * Loads the tags from the file.
     *
     * @return The list of tags loaded from the file.
     */
    public static ArrayList<String> loadTags() {
        ArrayList<String> tags = new ArrayList<>();
        File file = new File("./data/tags.txt");

        if (!file.exists()) {
            return tags; // Return empty list if no previous data
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tags.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved tags found.");
        } catch (Exception e) {
            System.out.println("Error loading tags: " + e.getMessage());
        }

        return tags;
    }

    /**
     * Converts a task to a string in the file format.
     *
     * @param task The task to convert.
     * @return The string representation of the task in the file format.
     */
    private static String taskToFileFormat(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + (d.isDone() ? "1" : "0") + " | " + d.getDescription() + " | " + d.getBy();
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + (e.isDone() ? "1" : "0") + " | " + e.getDescription() + " | " + e.getFrom() + " | " + e.getTo();
        }
        return "";
    }

    /**
     * Parses a task from a string in the file format.
     *
     * @param line The string in the file format.
     * @return The task parsed from the string.
     */
    private static Task parseTaskFromFile(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (type) {
                case "T":
                    Todo todo = new Todo(description);
                    if (isDone) todo.markAsDone();
                    return todo;
                case "D":
                    Deadline deadline = new Deadline(description, parts[3]);
                    if (isDone) deadline.markAsDone();
                    return deadline;
                case "E":
                    Event event = new Event(description, parts[3], parts[4]);
                    if (isDone) event.markAsDone();
                    return event;
                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Skipping corrupted line: " + line);
            return null;
        }
    }
}
