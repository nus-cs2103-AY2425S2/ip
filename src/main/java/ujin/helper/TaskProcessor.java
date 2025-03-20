package ujin.helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ujin.task.Deadline;
import ujin.task.Event;
import ujin.task.Task;
import ujin.task.TaskList;
import ujin.task.Todo;

/**
 * The {@code TaskProcessor} class provides utility methods for handling tasks
 * by converting them to text for file storage and reading them from files.
 * It is responsible for serializing tasks into a text format and deserializing
 * them back into their respective {@link Task} subclasses, such as {@link Todo},
 * {@link Deadline}, and {@link Event}. This class also handles reading and
 * writing tasks from/to a file, allowing task persistence.
 */
public class TaskProcessor {

    /**
     * A {@link DateTimeFormatter} used for formatting and parsing date and time
     * information in tasks.
     */
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Writes the tasks from the given {@link TaskList} to a file at the specified
     * {@code filePath}. Each task is converted to a text format using the
     * {@link #taskToText(Task)} method, and the tasks are appended to the file.
     *
     * @param taskList The {@link TaskList} containing tasks to write to the file.
     * @param filePath The path of the file where tasks will be saved.
     */
    public static void writeTasksToFile(TaskList taskList, String filePath) {
        boolean firstTime = true;
        int size = taskList.size();
        for (int i = 0; i < size; i++) {
            Task task = taskList.get(i);
            String line = taskToText(task);
            try (FileWriter writer = new FileWriter(filePath, !firstTime)) { // false means overwrite mode
                writer.write(line + "\n"); // Writing a single line
                firstTime = false;
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }
    }

    /**
     * Reads tasks from the file located at the specified {@code filePath} and
     * returns them as a {@link List} of {@link Task} objects. If the file does
     * not exist, it will attempt to create the necessary directories and the
     * file itself.
     *
     * @param filePath The path of the file from which tasks will be read.
     * @return A {@link List} of {@link Task} objects read from the file.
     */
    public static List<Task> readTasksFromFile(String filePath) {
        List<Task> li = new ArrayList<>();

        File file = new File(filePath);
        if (file.exists()) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                for (String line : lines) {
                    li.add(textToTask(line));
                }
                return li;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                if (!file.getParentFile().mkdirs()) {
                    System.out.println("Error creating folder: " + file.getParent());
                } else {
                    if (!file.createNewFile()) {
                        System.out.println("Error creating file: " + file.getAbsolutePath());
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return li;
    }

    /**
     * Converts a {@link Task} object to a text string representation suitable
     * for saving to a file. The format includes the task type (T, D, E), its
     * completion status, description, and for {@link Deadline} and {@link Event}
     * tasks, additional date/time information.
     *
     * @param task The {@link Task} object to convert to text.
     * @return A string representation of the task in a specific format.
     */
    public static String taskToText(Task task) {
        String text = "";
        if (task instanceof Todo) {
            text += "T";
        }
        if (task instanceof Deadline) {
            text += "D";
        }
        if (task instanceof Event) {
            text += "E";
        }
        text += " | ";
        text += (task.isDone() ? "1" : "0");
        text += " | ";
        if (task instanceof Todo) {
            text += task.description();
        }
        if (task instanceof Deadline) {
            text += task.description();
            text += " | ";
            text += ((Deadline) task).by().format(formatter);
        }
        if (task instanceof Event) {
            text += task.description();
            text += " | ";
            text += ((Event) task).start().format(formatter);
            text += " | ";
            text += ((Event) task).end().format(formatter);
        }
        return text;
    }

    /**
     * Converts a text string (formatted by {@link #taskToText(Task)}) back into
     * a {@link Task} object. The string is split by the delimiter "|" and
     * parsed based on the task type to create the appropriate task subclass..
     *
     * @param text The string representation of a task.
     * @return The corresponding {@link Task} object.
     */
    public static Task textToTask(String text) {
        Task task;
        String[] parts = text.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        switch (parts[0]) {
        case "T":
            task = new Todo(parts[2]);
            if (parts[0].equals("1")) {
                task.markAsDone();
            }
            break;
        case "D":
            task = new Deadline(parts[2], parts[3]);
            if (parts[0].equals("1")) {
                task.markAsDone();
            }
            break;
        case "E":
            task = new Event(parts[2], parts[3], parts[4]);
            if (parts[0].equals("1")) {
                task.markAsDone();
            }
            break;
        default:
            task = null;
        }
        return task;
    }
}
