package xuxin.main;

import java.io.*;
import xuxin.exception.DukeException;
import xuxin.task.Task;
import xuxin.task.Todo;
import xuxin.task.Deadline;
import xuxin.task.Event;

/**
 * Handles loading and saving of tasks to a file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves tasks to the specified file.
     * @param tasks The list of tasks to save.
     */
    public void saveTasks(TaskList tasks) throws DukeException {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs(); // Ensure directory exists

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : tasks.getTasks()) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error saving tasks.");
        }
    }

    /**
     * Loads tasks from the specified file.
     *
     * @param ui Take in the Ui to print error messages
     * @return A TaskList of tasks loaded from the file.
     */
    public TaskList loadTasks(Ui ui) throws DukeException {
        TaskList tasks = new TaskList();
        File file = new File(filePath);

        if (file.exists()) { file.delete(); }
        if (!file.exists()) return tasks; // No file? Return empty list.

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    tasks.addTask(parseTask(line)); // Try parsing each line
                } catch (DukeException e) {  // Catch duke.command.exception.DukeException here
                    ui.addMessage("Corrupt data detected. Deleting existing file.");
                    file.delete(); // Delete corrupted file
                    return new TaskList(); // Return empty list
                }
            }
        } catch (IOException e) {
            ui.addMessage("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    private Task parseTask(String line) throws DukeException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) throw new DukeException("Invalid file format.");

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                return new Deadline(description, parts[3], isDone);
            case "E":
                String[] timeParts = parts[3].split(" to ");
                return new Event(description, timeParts[0], timeParts[1], isDone);
            default:
                throw new DukeException("Unknown task type.");
        }
    }
}
