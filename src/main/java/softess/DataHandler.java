package softess;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Handles reading and writing task data to an external file.
 * This ensures data persists outside the JAR file.
 */
public class DataHandler {
    private final String filePath;

    /**
     * Constructs a new {@code DataHandler} and ensures the data file exists.
     */
    public DataHandler() {
        // Store in the user's home directory under ".softess/tasks.txt"
        String userHome = System.getProperty("user.home");
        String directoryPath = userHome + File.separator + ".softess";
        this.filePath = directoryPath + File.separator + "tasks.txt";


        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Error creating data file: " + e.getMessage());
        }
    }

    /**
     * Loads task data from the file.
     *
     * @return an {@code ArrayList} containing the loaded tasks
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner s = new Scanner(new File(filePath))) {
            while (s.hasNext()) {
                String[] taskData = s.nextLine().split("\\|");
                boolean status = Objects.equals(taskData[1], "1");

                switch (taskData[0]) {
                    case "TODO":
                        tasks.add(new ToDo(taskData[2], status));
                        break;
                    case "DEADLINE":
                        tasks.add(new Deadline(taskData[2], taskData[3], status));
                        break;
                    case "EVENT":
                        tasks.add(new Event(taskData[2], taskData[3], taskData[4], status));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Data file not found. Starting fresh.");
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param tasks the list of tasks to be saved
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void saveData(ArrayList<Task> tasks) throws IOException {
        try (FileWriter fw = new FileWriter(filePath, false)) {
            for (Task task : tasks) {
                fw.write(task.generateTextToFile() + System.lineSeparator());
            }
        }
    }
}
