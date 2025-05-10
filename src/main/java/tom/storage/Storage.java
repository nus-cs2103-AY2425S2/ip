package tom.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import tom.exception.FileException;
import tom.exception.TomCommandException;
import tom.parser.Parser;
import tom.task.Deadline;
import tom.task.Event;
import tom.task.Task;
import tom.task.Todo;
import tom.tasklist.TaskList;

/**
 * Handles the storage of tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The file path for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws TomCommandException If there is an error in the command.
     */
    public void saveFile(TaskList tasks) throws TomCommandException {
        assert tasks != null : "Task list should not be null";
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter taskWriter = new BufferedWriter(new FileWriter(filePath));
            String tasksText = "";
            for (Task task : tasks) {
                tasksText += task.toFileFormatString() + "\n";
            }
            taskWriter.write(tasksText);
            taskWriter.close();
        } catch (IOException e) {
            throw new FileException("file saving failed: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks from the file.
     *
     * @param tasks The list of tasks to be loaded.
     * @throws TomCommandException If there is an error in the command.
     */
    public void loadFile(TaskList tasks) throws TomCommandException {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new FileException("file cannot be created: " + e.getMessage());
            }
        }

        try {
            BufferedReader taskReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = taskReader.readLine()) != null && !line.isEmpty()) {
                Task task = fromFileFormatString(line);
                tasks.addTask(task);
            }
            taskReader.close();
        } catch (IOException e) {
            throw new FileException("file parsing failed: " + e.getMessage());
        }
    }

    /**
     * Converts a string from the file format to a Task object.
     *
     * @param line The string in file format.
     * @return The Task object.
     * @throws TomCommandException If there is an error in the command.
     */
    public Task fromFileFormatString(String line) throws TomCommandException {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        String status = parts[1];
        String description = parts[2];
        Task task;
        switch (type) {
        case "T": {
            task = new Todo(description);
            break;
        }
        case "D": {
            LocalDate end = Parser.stringToDate(parts[3], "MMM d yyyy");
            task = new Deadline(description, end);
            break;
        }
        case "E": {
            LocalDate start = Parser.stringToDate(parts[3], "MMM d yyyy");
            LocalDate end = Parser.stringToDate(parts[4], "MMM d yyyy");
            task = new Event(description, start, end);
            break;
        }
        default:
            throw new TomCommandException("Invalid task type in file.");
        }
        if (status.equals("X")) {
            task.markDone();
        }
        return task;
    }
}
