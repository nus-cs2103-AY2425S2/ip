package ricky;

import ricky.task.Task;
import ricky.task.TaskList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Handles the loading and saving of tasks to a file.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file to store tasks.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     * @throws RickyException If the file contains invalid tasks.
     * @throws IOException If an I/O error occurs.
     */
    public TaskList loadTasks() throws RickyException {
        TaskList tasks = new TaskList();
        if (!Files.exists(filePath)) {
            createFile();
        }
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Parser.parseSavedTask(line.split(" \\| "), tasks);
            }
        } catch (FileNotFoundException e) {
            throw new RickyException("File not found: " + filePath);
        } catch (IOException e) {
            throw new RickyException("Error reading file: " + filePath);
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws RickyException If an error occurs while writing to the file.
     */
    public void storeTasks(TaskList tasks) throws RickyException {
        if (!Files.exists(filePath)) {
            createFile();
        }
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                writer.write(task.storeInfo());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RickyException("Error writing to file: " + filePath);
        }
    }

    /**
     * Creates the file and its parent directories if they do not exist.
     */
    public void createFile() {
        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        } catch (IOException e) {
            System.out.println("Error creating file: " + filePath);
        }
    }
}
