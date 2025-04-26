package storage;

import exception.SunpterException;
import task.Task;
import parser.StorageParser;

import java.io.*;

import java.util.ArrayList;

/**
 * Stores tasks
 */
public class Storage {
    private static final String FILE_PATH = "./data/sunpter.txt";

    public Storage() {}

    /**
     * Saves tasks into a file
     * @param tasks The list of tasks to save
     */
    public void saveTasks(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs(); // Ensure directory exists

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.toStorageString() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new SunpterException("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Reads saved tasks from the file sunpter.txt and loads them into the chatbot
     * @return ArrayList<Task> The list of loaded tasks
     */
    public ArrayList<Task> loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {
            return readTasksFromFile();
        } catch (IOException e) {
            throw new SunpterException("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from storage
     * @return a list of previously stored tasks
     * @throws IOException
     */
    private ArrayList<Task> readTasksFromFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                addTaskFromLine(tasks, line);
            }
        }
        return tasks;
    }

    /**
     * Adds tasks to an arraylist
     */
    private void addTaskFromLine(ArrayList<Task> tasks, String line) {
        assert line != null : "Line cannot be null";
        if (line.trim().isEmpty()) {
            return;
        }
        Task task = StorageParser.parseStoredTask(line);
        assert task != null : "Parsed task should not be null";
        tasks.add(task);
    }

}
