package a.storage;
import java.io.*;
import java.util.ArrayList;

import a.task.*;

/**
 * Handles the loading and saving of tasks to a file for persistent storage.
 */
public class Storage {

    private final File file;

    /**
     * Constructs a Storage instance with a given file path.
     * Ensures that the file and its parent directories exist.
     *
     * @param filePath The relative or absolute path to the storage file.
     */

    public Storage(String filePath) {
        this.file = new File(filePath);
        ensureFileExists();
    }

    /**
     * Ensures that the storage file and its parent directory exist.
     * Creates them if they do not exist.
     */
    private void ensureFileExists() {
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException ignored) {
            System.out.print("error");
        }
    }

    /**
     * Loads tasks from the storage file into an ArrayList.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */


    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(parseTask(line));
            }
        } catch (IOException ignored) {
            System.out.print("error");
        }

        return tasks;
    }

    /**
     * Parses a line from the storage file and converts it into a Task object.
     *
     * @param line A single line from the storage file in the format: "T | 1 | task description".
     * @return A Task object representing the parsed line.
     */
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        Task task = switch (parts[0]) {
        case "T" -> new Todo(parts[2]);
        case "D" -> new Deadline(parts[2], parts[3]);
        case "E" -> new Event(parts[2], parts[3], parts[4]);
        default -> null;
        };
        if (task != null && parts[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Saves the current list of tasks to the storage file.
     * Overwrites the file with the latest task list.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void save(ArrayList<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                bw.write(task.toSaveFormat());
                bw.newLine();
            }
        } catch (IOException ignored) {
            System.out.print("error");
        }
    }
}
