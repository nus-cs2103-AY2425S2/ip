package gptzerofive.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import gptzerofive.exception.GptException;
import gptzerofive.exception.InvalidDateFormatException;
import gptzerofive.task.Deadline;
import gptzerofive.task.Event;
import gptzerofive.task.Task;
import gptzerofive.task.TaskList;
import gptzerofive.task.Todo;

/**
 * Handles storage of tasks in a file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @param taskList List of tasks.
     * @throws IOException
     * @throws GptException
     */
    public void loadFromFile(TaskList taskList) throws IOException, GptException, InvalidDateFormatException {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                Task task;
                switch (parts[0]) {
                case "T" -> task = new Todo(parts[2]);
                case "D" -> task = new Deadline(parts[2], parts[3]);
                case "E" -> task = new Event(parts[2], parts[3], parts[4]);
                default -> throw new GptException("Unknown task type.");
                }
                task.setDone(Boolean.valueOf(parts[1]));
                taskList.addTask(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            file.createNewFile();
            System.out.println("File not found.");
        }
    }

    /**
     * Saves tasks to the file.
     *
     * @param taskList List of tasks to save.
     */
    public void saveToFile(TaskList taskList) {
        try {
            File file = new File(filePath);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.getTask(i).convertToFileString() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
