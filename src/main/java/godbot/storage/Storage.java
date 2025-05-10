package godbot.storage;

import godbot.task.Task;
import godbot.task.ToDo;
import godbot.task.Deadline;
import godbot.task.Event;
import godbot.exception.GodBotException;
import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Storage class handles the reading and writing of tasks to a file,
 * allowing tasks to be persisted between program runs.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path for saving and loading tasks.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file at the specified file path.
     * If the file or parent directories do not exist, they are created.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while accessing the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("GodBot has caught an IO Exception while trying to read file");
            tasks.clear();
        }
        return tasks;
    }

    /**
     * Saves the given list of tasks to the file at the specified file path.
     * If the file or parent directories do not exist, they are created.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        }
    }

    /**
     * Parses a line from the file and converts it into a Task object.
     *
     * @param line The line from the file representing a task in a specific format.
     * @return The corresponding Task object, or null if the line format is invalid.
     */
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        if (type.equals("T")) {
            return new ToDo(description, isDone);
        } else if (type.equals("D")) {
            return new Deadline(description, parts[3], isDone);
        } else if (type.equals("E")) {
            return new Event(description, parts[3], parts[4], isDone);
        } else {
            return null;
        }
    }
}

