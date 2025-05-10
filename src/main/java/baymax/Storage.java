package baymax;

import java.io.*;

import baymax.task.*;

/**
 * Handles the loading and saving of tasks to a storage file.
 * This class is responsible for reading and writing tasks to a file
 * to maintain persistence between program runs.
 */
public class Storage {
    private static final String FILE_PATH = System.getProperty("user.home") + "/baymax_data/baymax.txt";
    private static final String DIRECTORY_PATH = System.getProperty("user.home") + "/baymax_data";

    private static final String ERROR_CREATING_FILE = "Error creating storage file: ";
    private static final String ERROR_SAVING_TASKS = "Error saving tasks: ";
    private static final String ERROR_LOADING_TASKS = "Error loading tasks: ";
    private static final String ERROR_CORRUPTED_TASK = "Skipping corrupted task: ";

    private File file;
    private File directory;

    /**
     * Constructs a {@code Storage} object and initializes the storage file.
     * If the required directory does not exist, it creates the directory.
     * If the storage file does not exist, it creates a new file.
     */
    public Storage() {
        file = new File(DIRECTORY_PATH + "/duke.txt");
        directory = new File(DIRECTORY_PATH);
        createStorageIfNotExists();
    }

    private void createStorageIfNotExists() {
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(ERROR_CREATING_FILE + e.getMessage());
        }
    }

    /**
     * Saves the current list of tasks to the storage file.
     * The file is overwritten each time this method is called.
     *
     * @param tasks The {@code TaskList} containing tasks to be saved.
     */
    public void saveTasks(TaskList tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) { // Overwrites file
            for (Task task : tasks.getTasks()) {
                writer.write(task.toFileString()); // Writes each task to file
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IOException(ERROR_SAVING_TASKS + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     * If the file does not exist, returns an empty {@code TaskList}.
     *
     * @return A {@code TaskList} containing the tasks loaded from the file.
     */
    public TaskList loadTasks() {
        File file = new File(FILE_PATH);
        TaskList tasks = new TaskList();

        if (!file.exists()) {
            return tasks;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.addTask(task);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses a line from the storage file into a {@code Task} object.
     * If the line is corrupted or unrecognized, it returns {@code null}.
     *
     * @param line The line from the file representing a stored task.
     * @return A {@code Task} object if successfully parsed, {@code null} otherwise.
     */
    public static Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isCompleted = parts[1].equals("true");

            switch (type) {
            case "T":
                return new Todo(parts[2], isCompleted);
            case "D":
                return new Deadline(parts[2], parts[3], isCompleted);
            case "E":
                return new Event(parts[2], parts[3], parts[4], isCompleted);
            default:
                return null;
            }
        } catch (Exception e) {
            System.out.println("Skipping corrupted task: " + line);
            return null;
        }
    }
}
