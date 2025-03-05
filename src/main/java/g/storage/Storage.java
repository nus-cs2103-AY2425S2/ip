package g.storage;
import java.io.*;
import java.util.ArrayList;
import g.tasks.*;

/**
 * Handles the reading and writing of tasks to a storage file.
 * This class ensures task data is persisted across application sessions.
 */
public class Storage {
    private final String filePath;

    /**
     * Initializes a Storage object and ensures the storage file exists.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        File directory = new File(file.getParent());

        try {
            if (!directory.exists()) {
                directory.mkdirs(); // Create directory if it does not exist
            }
            if (!file.exists()) {
                file.createNewFile(); // Create file if it does not exist
            }
        } catch (IOException e) {
            System.out.println("Error initializing storage file.");
        }
    }

    /**
     * Loads tasks from the storage file into an ArrayList.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        tasks.add(new Todo(parts[2], parts[1].equals("1")));
                        break;
                    case "D":
                        tasks.add(new Deadline(parts[2], parts[3], parts[1].equals("1")));
                        break;
                    case "E":
                        tasks.add(new Event(parts[2], parts[3], parts[4], parts[1].equals("1")));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the current task list to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void save(ArrayList<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath))) {
            for (Task task : tasks) {
                bw.write(task.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }
}
