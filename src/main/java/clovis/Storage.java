package clovis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import clovis.task.Deadline;
import clovis.task.Event;
import clovis.task.Task;
import clovis.task.ToDo;

/**
 * The {@code Storage} class handles the saving of tasks to a file and retrieval of tasks from a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a {@code Storage} instance with the specified file path.
     *
     * @param filePath the path to the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by {@code filePath}.
     *
     * @return an {@code ArrayList} of tasks loaded from the file.
     * @throws ClovisException if an error occurs while reading the file.
     */
    public ArrayList<Task> loadTasks() throws ClovisException {
        ensureFileExist();
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            throw new ClovisException("Error loading file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves tasks into the file specified by {@code filePath}.
     *
     * @param tasks takes a list of tasks to be saved.
     * @throws ClovisException if an error occurs while writing to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws ClovisException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            tasks.stream()
                    .map(Task::toFileFormat)
                    .forEach(line -> {
                        try {
                            bw.write(line);
                            bw.newLine();
                        } catch (IOException e) {
                            throw new RuntimeException("Error writing task: " + line, e);
                        }
                    });
        } catch (IOException e) {
            throw new ClovisException("Error saving file: " + e.getMessage());
        }
    }

    /**
     * Ensures a file exist in the specified path, else create one.
     */
    public void ensureFileExist() {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
    }

    /**
     * Parses a line from the storage file and converts it into a corresponding {@code Task} object.
     *
     * @param line the line from the file representing a task.
     * @return the corresponding {@code Task} object, or {@code null} if the task type is unknown.
     */
    public Task parseTaskFromFile(String line) {
        String[] input = line.split(" \\| ");
        String taskType = input[0];
        boolean isDone = input[1].equals("1");
        String taskDescription = input[2];

        switch (taskType) {
        case "T":
            return new ToDo(taskDescription, isDone);
        case "D":
            return new Deadline(taskDescription, isDone, input[3]);
        case "E":
            return new Event(taskDescription, isDone, input[3], input[4]);
        default:
            System.out.println("Unknown task type: " + taskType);
            return null;
        }
    }
}
