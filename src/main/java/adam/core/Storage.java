package adam.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import adam.exceptions.AdamException;
import adam.tasks.Task;

/**
 * Represents the storage of tasks.
 */
public class Storage {
    /** Default path of log file if none is specified */
    private static final String DEFAULT_PATH_FILE = "./data/adam_log.log";

    /** Path of log file */
    private final String logPathFile;

    /**
     * Constructor for Storage.
     *
     * @param path Path of log file
     */
    public Storage(String path) {
        this.logPathFile = path;
    }

    /**
     * Alternate constructor for Storage with default path.
     */
    Storage() {
        this.logPathFile = Storage.DEFAULT_PATH_FILE;
    }

    /**
     * Loads the log file. If any error occurs, an empty ArrayList is returned.
     *
     * @return ArrayList of tasks from log file
     */
    public ArrayList<Task> loadLog() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(this.logPathFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Task.fromLog(line);
                tasks.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // No previous tasklist: do nothing
        } catch (AdamException e) {
            System.out.println("Error loading task list from file: " + e + ". Ignoring file.");
            tasks = new ArrayList<>();
        }
        return tasks;
    }

    /**
     * Saves the log file.
     *
     * @param tasks Tasks to save
     */
    public void saveLog(ArrayList<Task> tasks) {
        try {
            File file = new File(this.logPathFile);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(this.logPathFile);
            for (Task task : tasks) {
                writer.write(task.toLogString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving task list to file: " + e);
        }
    }
}
