package stonks.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.util.ArrayList;

import stonks.task.Task;
import stonks.task.Todo;
import stonks.task.Event;
import stonks.task.Deadline;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads the data from the hard disk when the chatbot starts up.
     * @return list of tasks loaded
     */
    public ArrayList<Task> load() {
        Path path = Paths.get(this.filepath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Error initializing data file: " + e.getMessage());
                return null;
            }
        }
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) continue;
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task = switch (type) {
                    case "T" -> new Todo(description);
                    case "D" -> new Deadline(description, parts.length > 3 ? LocalDate.parse(parts[3]) : null);
                    case "E" -> new Event(description, parts.length > 3 ? LocalDate.parse(parts[3]) : null, parts.length > 4 ? LocalDate.parse(parts[4]) : null);
                    default -> null;
                };
                if (task != null) {
                    if (isDone) {
                        task.markDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading data file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the tasks in the hard disk
     * @param tasks list of tasks to save
     */
    public void save(ArrayList<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filepath))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
