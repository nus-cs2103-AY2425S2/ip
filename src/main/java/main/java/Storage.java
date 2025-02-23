package main.java;

import java.io.*;
import java.util.ArrayList;


public class Storage {
    /* The path of the file that records all the tasks */
    private static final String FILE_PATH = "./data/tasks.txt";

    public Storage() {
        ensureFileExists();
    }
    /**
     * Ensures that the file exists. 
     * If it does not exist, a new directory and a new file will be created under the specified path. 
     */
    private void ensureFileExists() {
        try {
            File file = new File(FILE_PATH);
            File directory = file.getParentFile();

            if (directory != null && !directory.exists()) {
                directory.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error when creating the file: " + e.getMessage());
        }
    }

    /**
     * Saves the task to the txt file. 
     * 
     * @param ls the tasklist object
     */
    public void save(Tasklist ls) {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            ArrayList<Task> list = ls.getter();
            for (Task task: list) {
                writer.write(task.toFileForm());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error when saving the task: " + e.getMessage());
        }
    }

    public Tasklist load() {
        Tasklist tl = new Tasklist(new ArrayList<>());
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                char type = line.charAt(0);
                String[] parts = line.split("\\|");
                String status = parts[1].trim();
                boolean isDone;
                if (status.equals("1")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                String description = parts[2].trim();
                if (type == 'T') {
                    Task t = Todo.fromFileForm(isDone, description);
                    tl.addTask(t);
                } else if (type == 'D') {
                    String by = parts[3].trim();
                    Task t = Deadline.fromFileForm(isDone, description, by);
                    tl.addTask(t);
                } else {
                    String from = parts[3].trim();
                    String to = parts[4].trim();
                    Task t = Event.fromFileForm(isDone, description, from, to);
                    tl.addTask(t);
                }
            }
        } catch (IOException e) {
            System.out.println("Fail to load the tasks from the txt file: " + e.getMessage());
        }
        return tl;

    }
}
