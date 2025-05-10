package steve.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import steve.tasks.Deadline;
import steve.tasks.Event;
import steve.tasks.Task;
import steve.tasks.ToDo;

/**
 * Handles the loading and parsing of tasks from a file.
 * This class is responsible for reading the tasks stored in a file and converting them into task objects.
 */
public class FileHandler {
    private static final String DATA_DIRECTORY = System.getProperty("user.home") + File.separator + "steve-data";
    private static final String FILE_NAME = "steve.txt";
    private static final String FILE_PATH = DATA_DIRECTORY + File.separator + FILE_NAME;
    private String filePath;

    /**
     * Constructs a FileHandler with the specified file path.
     *
     * @param filePath the path to the file containing the tasks
     */
    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses a line from the file and creates the appropriate task object based on the task type.
     *
     * @param line the line to be parsed
     * @return a Task object corresponding to the parsed line, or null if the task type is unknown
     */
    private static Task parseTask(String line) {
        String firstWord = line.split(":")[0];
        String restOfLine = line.substring(firstWord.length() + 1).trim();
        switch (firstWord) {
        case "Todo":
            return new ToDo(restOfLine);
        case "Deadline":
            return new Deadline(restOfLine);
        case "Event":
            return new Event(restOfLine);
        default:
            System.err.println("Unknown task type: " + firstWord);
            return null;
        }
    }

    /**
     * Loads tasks from the file and returns them as a list of Task objects.
     *
     * @return a list of Task objects loaded from the file
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File taskFile = new File(FILE_PATH);

        if (!taskFile.exists()) {
            System.out.println("No saved tasks found at: " + FILE_PATH);
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(taskFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line); // Parse the line into a Task object
                if (task != null) {
                    tasks.add(task); // Add to the tasks list
                }
            }
            System.out.println("Loaded " + tasks.size() + " tasks from: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}
