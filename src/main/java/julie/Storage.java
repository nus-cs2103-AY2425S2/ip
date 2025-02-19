package julie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import julie.task.Deadline;
import julie.task.Event;
import julie.task.Task;
import julie.task.ToDo;



/**
 * Handles file storage operations for loading and saving tasks.
 * This class is responsible for persisting task data to a file and retrieving it upon initialization.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a {@code Storage} object with the specified file path.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file. If the file does not exist, it will be created.
     *
     * @return A list of tasks loaded from the file.
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            return tasks;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                assert task != null : "Parsed task is null for line: " + line;
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        assert tasks != null : "Loaded task list should not be null";
        return tasks;
    }

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(List<Task> tasks) {
        assert tasks != null : "Tasks list should not be null before saving";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                assert task != null : "Task to be saved is null";
                bw.write(task.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the file and converts it into a {@code Task}.
     * This method reads the saved task format and reconstructs the appropriate {@code Task} object.
     *
     * @param line The line of text from the file representing a task.
     * @return A reconstructed {@code Task} object, or {@code null} if the line is corrupted.
     */
    private Task parseTask(String line) {
        assert line != null && !line.isEmpty() : "Line to parse should not be null or empty";
        try {
            String[] parts = line.split(" \\| ");
            assert parts.length >= 3 : "Malformed task entry: " + line;

            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;
            switch (type) {
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
                task.markDone();
            }
            return task;
        } catch (Exception e) {
            System.out.println("Skipping corrupt task entry: " + line);
            return null;
        }
    }


}

