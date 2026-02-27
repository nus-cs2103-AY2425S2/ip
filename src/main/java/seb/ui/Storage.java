package seb.ui;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    public Storage(String filepath) {
        assert filepath != null : "Filepath cannot be null!";

        this.filePath = filepath;
    }

    /**
     * Loads tasks from the file into ArrayList
     * Creates the file and directory if they don't exit
     *
     * @return tasks in an ArrayList
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                // create directory if file does not exist
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;
            }
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Task task = parseTaskLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses tasks from file format to Task object
     *
     * @param line String containing details of each task
     * @return new Task from each String of tasks in file format
     */
    public Task parseTaskLine(String line) {

        String[] parts = line.split(" \\| ");

        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                String due = parts[3];
                return new Deadline(description, due, isDone);
            case "E":
                String start = parts[3];
                String end = parts[4];
                return new Event(description, start, end, isDone);
            default:
                System.out.println("Umm I cant identify that task " + taskType);
                return null;
        }
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task t : tasks) {
                writer.println(t.toFileFormat());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
