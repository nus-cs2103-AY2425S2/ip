package model.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import model.exception.StorageDecodeException;
import model.exception.StorageIOException;
import model.task.Deadline;
import model.task.Event;
import model.task.Task;
import model.task.TaskList;
import model.task.Todo;
import utils.DateTimeUtils;

public class Storage {

    private static final String DEFAULT_DIR_PATH = "data";
    private static final String DEFAULT_FILE_PATH = "alice.txt";

    private final String dir;
    private final String file;

    /**
     * Constructs a Storage object with default directory and file paths.
     */
    public Storage() {
        this.dir = DEFAULT_DIR_PATH;
        this.file = DEFAULT_FILE_PATH;
    }

    /**
     * Constructs a Storage object with specified directory and file paths. Note
     * that these paths are relative.
     *
     * @param dir The directory path.
     * @param file The file path.
     */
    public Storage(String dir, String file) {
        this.dir = dir;
        this.file = file;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return The list of tasks.
     * @throws StorageIOException If an I/O error occurs.
     */
    public TaskList loadTasks() throws StorageIOException {
        Path dirPath = Paths.get(this.dir);
        Path filePath = dirPath.resolve(this.file);
        TaskList tasks = new TaskList();
        List<String> lines;

        try {
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new StorageIOException();
        }

        for (String line : lines) {
            try {
                Task task = decodeTask(line);
                tasks.addTask(task);
            } catch (StorageDecodeException e) {
            }
        }

        return tasks;
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param tasks The list of tasks.
     * @throws StorageIOException If an I/O error occurs.
     */
    public void saveTasks(TaskList tasks) throws StorageIOException {
        Path dirPath = Paths.get(this.dir);
        Path filePath = dirPath.resolve(this.file);

        try {
            Files.write(filePath, tasks.toDataString().getBytes());
        } catch (IOException e) {
            throw new StorageIOException();
        }
    }

    /**
     * Deletes the storage file.
     *
     * @throws StorageIOException If an I/O error occurs.
     */
    public void deleteFile() throws StorageIOException {
        Path dirPath = Paths.get(this.dir);
        Path filePath = dirPath.resolve(this.file);

        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new StorageIOException();
        }
    }

    /**
     * Decodes a task from a data string. The format is specified in
     * Task.toDataString().
     *
     * @param taskString The task string.
     * @return The decoded task.
     * @throws StorageDecodeException If the task string format is incorrect.
     */
    public static Task decodeTask(String taskString) throws StorageDecodeException {
        String[] taskParts = taskString.split("\\|");
        boolean isMarked = taskParts[1].equals("1");
        String name = taskParts[2];
        switch (taskParts[0]) {
            case "T" -> {
                return new Todo(name, isMarked);
            }
            case "D" -> {
                LocalDateTime by = DateTimeUtils.parseDateTime(taskParts[3]);
                return new Deadline(name, by, isMarked);
            }
            case "E" -> {
                LocalDateTime from = DateTimeUtils.parseDateTime(taskParts[3]);
                LocalDateTime to = DateTimeUtils.parseDateTime(taskParts[4]);
                return new Event(name, from, to, isMarked);
            }
            default ->
                throw new StorageDecodeException();
        }
    }

}
