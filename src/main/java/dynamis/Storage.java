package dynamis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import java.util.Scanner;

/**
 * Handles the saving and loading of tasks to a locally stored file.
 */
public class Storage {
    private final String filePath;

    private static final String ERROR_MESSAGE = "Something went wrong. ";

    /**
     * Constructs a storage object with a given file to save and load tasks from.
     *
     * @param filePath The file path of the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if the file in the given file path exists, otherwise create it.
     */
    public void initializeFile() {
        try {
            File f = new File(this.filePath);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Saves the tasks to the file in the filePath property.
     *
     * @param tasks The list of tasks to save in the file.
     */
    public void saveToFile(ArrayList<Task> tasks) throws IOException {
        String tempStorage = "";
        Parser parser = new Parser();
        for (int i = 0; i < tasks.size(); i++) {
            tempStorage = tempStorage + parser.serialiseTask(tasks.get(i)) + "\n";
        }
        try {
            writeToFile(this.filePath, tempStorage);
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
        }
    }

    /**
     * Loads all the tasks from the file in the given filepath.
     *
     * @return the list of tasks.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        Parser parser = new Parser();
        while (s.hasNext()) {
            Task task = parser.deserializeTask(s.nextLine());
            if (task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }
}
