package tasker;

import static tasker.Parser.parseStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import tasker.exception.TaskerException;
import tasker.task.Task;
import tasker.task.TaskList;

/**
 * Storage and retrieval of tasks.
 */
public class Storage {
    /** Path of the file */
    private Path path;

    /**
     * Class constructor.
     *
     * @param path The path of the file to use.
     */
    public Storage(String path) throws TaskerException {
        this.path = Paths.get(path);

        try {
            if (!Files.exists(this.path)) {
                Files.createDirectories(this.path.getParent());
                Files.createFile(this.path);
            }
        } catch (IOException e) {
            throw new TaskerException("Failed to create task storage.");
        }
    }

    /**
     * Saves the task list to the file.
     *
     * @param tasks The task list to save the contents of.
     */
    public void save(TaskList tasks) throws TaskerException {
        LinkedList<String> lines = new LinkedList<>();

        for (Task task : tasks.getTasks()) {
            lines.add(task.toStorage());
        }

        try {
            Files.write(path, lines);
        } catch (IOException e) {
            throw new TaskerException("Failed to save tasks to storage.");
        }
    }

    /**
     * Returns a list of tasks in this storage.
     *
     * @returns List of task in storage.
     * @throws TaskerException If the stored tasks cannot be read.
     */
    public List<Task> getTasks() throws TaskerException {
        LinkedList<Task> tasks = new LinkedList<>();

        try {
            for (String line : Files.readAllLines(this.path)) {
                tasks.add(parseStorage(line));
            }
        } catch (IOException e) {
            throw new TaskerException("Failed to read tasks from storage.");
        }

        return tasks;
    }
}
