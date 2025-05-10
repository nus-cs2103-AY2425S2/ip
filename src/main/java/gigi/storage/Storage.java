package gigi.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import gigi.Parser;
import gigi.exceptions.GigiException;
import gigi.tasks.Deadlines;
import gigi.tasks.Events;
import gigi.tasks.Task;
import gigi.tasks.Tasklist;
import gigi.tasks.ToDos;

/**
 * Handles file storage operations for saving and loading tasks.
 * This class is responsible for reading and writing tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage instance with a specified file path.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void ensureFileExists() throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            boolean dirCreated = parentDir.mkdirs();
            if (!dirCreated) {
                System.out.println("MEOW: Unable to create directories for file storage.");
            }
        }

        if (!file.exists()) {
            boolean fileCreated = file.createNewFile();
            if (fileCreated) {
                System.out.println("Storage file created: " + filePath);
            } else {
                System.out.println("MEOW: Unable to create storage file.");
            }
        }
    }

    /**
     * Converts a formatted task string from storage into a Task object.
     *
     * @param line The stored task string.
     * @return The corresponding Task object.
     * @throws GigiException If the task type is unknown.
     */
    public static Task convertToTask(String line) throws GigiException {

        String[] info = line.split(" \\| ");

        if (info.length < 3) {
            throw new GigiException("MEOW! Malformed task data: " + line);
        }

        String taskType = info[0];
        boolean isDone = Boolean.parseBoolean(info[1]);

        return switch (taskType) {
        case ToDos.ICON_TODO -> new ToDos(info[2], isDone);
        case Deadlines.ICON_DEADLINE -> new Deadlines(info[2],
                Parser.parseDateTime(info[3]),
                isDone);
        case Events.ICON_EVENT -> new Events(info[2],
                Parser.parseDateTime(info[3]),
                Parser.parseDateTime(info[4]),
                isDone);
        default -> throw new GigiException("Unknown task type: " + taskType);
        };
    }

    /**
     * Loads tasks from the specified file and returns them as a list.
     * If the file does not exist, an empty list is returned.
     *
     * @return A list of tasks retrieved from the file.
     * @throws GigiException If an error occurs while reading the file.
     */
    public Tasklist loadTasksFromFile() throws GigiException, IOException {
        Tasklist tasks = new Tasklist();
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.addTask(convertToTask(line));
            }
        } catch (IOException e) {
            throw new GigiException("Error loading file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the specified file.
     * Each task is converted to a string format before being written to the file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        ensureFileExists();

        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                pw.println(task.convertToText());
            }
        }
    }
}
