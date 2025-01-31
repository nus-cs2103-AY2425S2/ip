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
<<<<<<< HEAD
 * Handles the storage of tasks in a file.
=======
 * Handles loading and saving tasks to a storage file.
>>>>>>> branch-A-CodingStandard
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
<<<<<<< HEAD
     * @param filePath The file path where tasks are stored.
=======
     * @param filePath The path where tasks are stored.
>>>>>>> branch-A-CodingStandard
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
<<<<<<< HEAD
     * Saves the given list of tasks to a file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an error occurs while writing to the file.
=======
     * Saves the list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an error occurs during file writing.
>>>>>>> branch-A-CodingStandard
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(serializeTask(task) + System.lineSeparator());
            }
        }
    }

    /**
<<<<<<< HEAD
     * Loads tasks from the file and returns them as a list.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an error occurs while reading the file.
=======
     * Loads the list of tasks from the storage file.
     *
     * @return A list of tasks retrieved from the file.
     * @throws IOException If an error occurs during file reading.
>>>>>>> branch-A-CodingStandard
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
<<<<<<< HEAD
     * Serializes a task into a string format for storage.
     *
     * @param task The task to be serialized.
     * @return A string representation of the task.
=======
     * Serializes a Task object into a formatted string for storage.
     *
     * @param task The task to serialize.
     * @return The serialized string representation of the task.
>>>>>>> branch-A-CodingStandard
     */
    private String serializeTask(Task task) {
        String typeCode = "";
        String extraData = "";

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

        return typeCode + " | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + extraData;
    }

    /**
<<<<<<< HEAD
     * Deserializes a task from a string format.
     *
     * @param line The string representation of the task.
     * @return The deserialized task, or {@code null} if parsing fails.
=======
     * Deserializes a line from the storage file into a Task object.
     *
     * @param line The stored task data in string format.
     * @return The corresponding Task object, or null if the format is invalid.
>>>>>>> branch-A-CodingStandard
     */
    private Task deserializeTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                System.out.println("WARNING: Skipping invalid task format: " + line);
                return null;
            }

            String typeCode = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;
            switch (typeCode) {
                case "T":
                    task = new ToDo(description);
                    break;
                case "D":
                    task = new Deadline(description, parts[3]);
                    break;
                case "E":
                    task = new Event(description, parts[3], parts[4]);
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
