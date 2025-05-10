package kaji;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import kaji.task.Deadline;
import kaji.task.Event;
import kaji.task.Task;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        assert !filePath.isEmpty() : "FILE_PATH should not be empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks saved in a specific file.
     *
     * @return task list loaded from file.
     * @throws KajiException If an error occurs when loading file
     */
    public ArrayList<Task> load() throws KajiException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            createStorageFile(file);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                TaskList.addTask(taskList, line);
            }
        } catch (IOException e) {
            throw new KajiException("Error reading file: " + e.getMessage());
        }

        return taskList;
    }

    /**
     * Creates new storage file if it does not exist.
     *
     * @param file The file to be created.
     * @throws KajiException If an error occurs when creating file.
     */
    public void createStorageFile(File file) throws KajiException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new KajiException("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Saves the tasks to a file.
     *
     * @param taskList the task list containing tasks to be saved
     */
    public void save(TaskList taskList) throws KajiException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskList.taskList) {
                String taskString = "";
                if (task.getType().equals("T")) {
                    taskString = String.format(
                            "%s | %s | %s ",
                            task.getType(), task.isDone(), task.getDescription()
                    );
                }
                if (task.getType().equals("D")) {
                    Deadline t = (Deadline) task;
                    taskString = String.format(
                            "%s | %s | %s | %s ",
                            task.getType(), t.getDeadline(), t.getDescription(), t.getDeadline()
                    );
                }
                if (task.getType().equals("E")) {
                    Event e = (Event) task;
                    taskString = String.format(
                            "%s | %s | %s | %s | %s ",
                            task.getType(), task.isDone(), task.getDescription(), e.getStart(), e.getEnd()
                    );
                }
                taskString += "| " + task.getTagsString();
                writer.write(taskString);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new KajiException("Error writing to file: " + e.getMessage());
        }
    }
}
