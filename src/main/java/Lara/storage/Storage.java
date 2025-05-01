/**
 * The Storage class handles the loading and saving of tasks from/to a file.
 * It ensures persistence of task data across sessions.
 *
 * Methods include:
 * - load(): Reads tasks from file.
 * - save(): Saves tasks to file.
 *
 * @author Maliha Haque
 * @version 1.0
 */

package Lara.storage;

import Lara.exception.LaraException;
import Lara.ui.Task;
import Lara.ui.Event;
import Lara.ui.Deadline;
import Lara.ui.Todo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {
    private static final String DIRECTORY_PATH = "src/main/data";
    private static final String FILE_NAME = "tasks.txt";
    private String filePath;

    public Storage() {
        this.filePath = DIRECTORY_PATH + "/" + FILE_NAME;
        ensureDirectoryExists();
        ensureFileExists();
    }

    private void ensureDirectoryExists() {
        try {
            Files.createDirectories(Paths.get(DIRECTORY_PATH)); // ✅ Ensure directory exists
        } catch (IOException e) {
            System.out.println("Warning: Unable to create directory for tasks file.");
        }
    }

    private void ensureFileExists() {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Warning: Unable to create file for tasks.");
        }
    }
    public ArrayList<Task> load() throws LaraException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new LaraException("Error: Unable to load file.");
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws LaraException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new LaraException("Error: Unable to save tasks.");
        }
    }

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                Task todo = new Todo(description);
                if (isDone) todo.markAsDone();
                return todo;
            case "D":
                Task deadline = new Deadline(description, fixDateFormat(parts[3]));
                if (isDone) deadline.markAsDone();
                return deadline;
            case "E":
                Task event = new Event(description, fixDateFormat(parts[3]), fixDateFormat(parts[4]));
                if (isDone) event.markAsDone();
                return event;
            default:
                throw new IllegalArgumentException("Invalid task type in file.");
        }
    }


    private String fixDateFormat(String date) {
        if (date.contains("T")) {
            return date.replace("T", " ");
        }
        return date;
    }

}




























































