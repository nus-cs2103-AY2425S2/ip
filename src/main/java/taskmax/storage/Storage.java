package taskmax.storage;

import taskmax.task.Deadline;
import taskmax.task.Event;
import taskmax.task.Task;
import taskmax.task.ToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading and saving tasks to a storage file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an error occurs during file writing.
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        assert tasks != null : "Tasks should not be null";
        saveTasks(tasks.toArray(new Task[0]));
    }

    /**
     * Saves tasks using varargs.
     *
     * @param tasks The tasks to be saved.
     * @throws IOException If an error occurs during file writing.
     */
    public void saveTasks(Task... tasks) throws IOException {
        assert tasks != null : "Tasks to save should not be null";
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(serializeTask(task) + System.lineSeparator());
            }
        }
    }

    /**
     * Loads the list of tasks from the storage file.
     *
     * @return A list of tasks retrieved from the file.
     * @throws IOException If an error occurs during file reading.
     */
    public List<Task> loadTasks() throws IOException {
        File file = new File(filePath);
        List<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = deserializeTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    /**
     * Serialises a Task object into a formatted string for storage.
     *
     * @param task The task to serialize.
     * @return The serialized string representation of the task.
     */
    private String serializeTask(Task task) {
        assert task != null : "Task should not be null";
        String typeCode = "";
        String extraData = "";
        String priority = String.valueOf(task.getPriority());

        if (task instanceof ToDo) {
            typeCode = "T";
        } else if (task instanceof Deadline) {
            typeCode = "D";
            extraData = " | " + ((Deadline) task).getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else if (task instanceof Event) {
            typeCode = "E";
            extraData = " | " + ((Event) task).getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                    + " | " + ((Event) task).getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        return typeCode + " | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + priority + extraData;
    }

    /**
     * Deserialises a line from the storage file into a Task object.
     *
     * @param line The stored task data in string format.
     * @return The corresponding Task object, or null if the format is invalid.
     */
    private Task deserializeTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 4) {
                System.out.println("WARNING: Skipping invalid task format: " + line);
                return null;
            }

            String typeCode = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            int priority = Integer.parseInt(parts[3]);

            Task task = null;
            switch (typeCode) {
                case "T":
                    task = new ToDo(description, priority);
                    break;
                case "D":
                    task = new Deadline(description, parts[4], priority);
                    break;
                case "E":
                    task = new Event(description, parts[4], parts[5], priority);
                    break;
                default:
                    return null;
            }

            if (isDone) {
                task.markAsDone();
            }
            return task;
        } catch (Exception e) {
            System.out.println("WARNING: Error parsing task: " + line);
            return null;
        }
    }
}
