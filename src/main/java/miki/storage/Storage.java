package miki.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import miki.exception.MikiException;
import miki.task.Task;

/**
 * Represents a storage object that handles the loading and saving of tasks to a
 * file.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a new storage object with the specified file path.
     *
     * @param filePath The file path to the file to load and save tasks from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the file path.
     *
     * @return A list of tasks loaded from the file.
     * @throws MikiException If there is an error loading tasks from the file.
     */
    public List<Task> load() throws MikiException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File parentDir = file.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs();
                }
                file.createNewFile();
                return tasks;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                tasks.add(Task.parseTaskFromFile(line));
            }
            br.close();
        } catch (IOException e) {
            throw new MikiException("Error loading tasks from file.\n" + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the specified list of tasks to the file specified in the file path.
     *
     * @param tasks The list of tasks to save to the file.
     * @throws MikiException If there is an error saving tasks to the file.
     */
    public void save(List<Task> tasks) throws MikiException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : tasks) {
                writer.write(task.toStorageFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new MikiException("Error saving tasks to file.");
        }
    }
}
