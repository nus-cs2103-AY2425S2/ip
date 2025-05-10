package charlie;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * The Storage class is responsible for loading and saving tasks from and to a file.
 * It ensures that the file exists before attempting any read or write operations.
 */
class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    private void ensureFileExists() {
        try {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error initializing storage file.");
            exit(1);
        }
    }

    /**
     * Loads the tasks from the storage file and returns them as a list.
     * If the file doesn't exist or there's an error reading, an empty list is returned.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        ensureFileExists();
        if (file.exists()) {
            try (Scanner reader = new Scanner(file)) {
                while (reader.hasNextLine()) {
                    tasks.add(Task.addFromFile(reader.nextLine()));
                }
            } catch (IOException e) {
                System.out.println("Error reading from file");
            }
        }
        return tasks;
    }

    /**
     * Saves the provided list of tasks to the storage file.
     * If an error occurs while writing, an error message is displayed.
     *
     * @param tasks The list of tasks to be saved to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try (FileWriter fw = new FileWriter(file)) {
            for (Task task : tasks) {
                fw.write(task.writeToFile());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
}
