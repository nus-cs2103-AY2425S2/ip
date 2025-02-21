package bobandsteve.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import bobandsteve.exception.BobAndSteveException;
import bobandsteve.tasklist.TaskList;
/**
 * Handles the loading and saving of task data to a file.
 * This class provides functionality to read from and write to a specific file,
 * managing the storage and retrieval of task-related information in the BobAndSteve application.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a new Storage object with the specified file path.
     *
     * @param filePath The path of the file where task data will be stored or loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task file from the specified file path.
     * If the file does not exist, it creates a new file in the default data directory.
     *
     * @return The file object representing the loaded or created file.
     * @throws BobAndSteveException If an error occurs during the file loading process.
     */
    public File load() throws BobAndSteveException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File directory = new File("./data");
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file = new File(directory, "bobAndSteve.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
            }
            return file;
        } catch (IOException error) {
            throw new BobAndSteveException("Failed to load or create the file. Please check file path permissions "
                    + "and ensure the 'data' directory is accessible.");
        }
    }

    /**
     * Writes the current task list to the file specified in the constructor.
     *
     * @param taskList The task list to be written to the file.
     */
    public void writeFile(TaskList taskList) {
        try {
            String newData = taskList.toString();
            assert newData != null : "Expected to get a return data not null";
            FileWriter fw = new FileWriter("./data/bobAndSteve.txt");
            fw.write(newData);
            fw.close();
        } catch (IOException error) {
            System.out.println("Error writing file");
        }
    }
}
