package nguyen;

import java.io.*;
import java.util.ArrayList;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private final String filePath;
    private final File file;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path must not be null or empty";
        this.filePath = filePath;
        this.file = new File(filePath);
        checkFileExists();
    }

    /**
     * Check that the file and its parent directory exist.
     */
    private void checkFileExists() {
        try {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error ensuring storage file exists: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws NguyenException If an error occurs while reading the file.
     */
    public ArrayList<Task> load() throws NguyenException {
        ArrayList<Task> taskList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Parser.parseTask(line);
                taskList.add(task);
            }
        } catch (IOException e) {
            throw new NguyenException("Error loading tasks: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Saves the tasks from the TaskList to the file.
     *
     * @param taskList The TaskList containing tasks to be saved.
     * @throws NguyenException If an error occurs while writing the file.
     */
    public void saveTask(TaskList taskList) throws NguyenException {
        assert taskList != null : "TaskList must not be null";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.get(i).toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new NguyenException("Error saving tasks: " + e.getMessage());
        }
    }
}
