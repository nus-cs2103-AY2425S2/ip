package duchess;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Handles saving and loading tasks from a file.
 */
public class Storage {
    private String filePath;
    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the given task list to a file.
     *
     * @param taskList The TaskList object containing tasks to be saved.
     */
    public void saveList(TaskList taskList) {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < taskList.size(); ++i) {
                Task task = taskList.get(i);
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from a file and returns them as a TaskList.
     * If the file does not exist, an empty TaskList is returned.
     *
     * @return A TaskList containing tasks loaded from the file.
     */
    public TaskList loadList() {
        File file = new File(this.filePath);
        TaskList taskList = new TaskList();

        if (!file.exists()) {
            System.out.println("No existing task file found.");
            return taskList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        taskList.sort();
        return taskList;
    }

    /**
     * Parses a line from the storage file and converts it into a Task object.
     *
     * @param s The line of text representing a task.
     * @return The corresponding Task object, or null if the entry is invalid.
     * @throws Exception If there is an error parsing the task data.
     */
    public Task parseTask(String s) throws Exception {
        try {
            String[] parts = s.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String taskName = parts[2];

            Task task;
            if (type.equals("T")) {
                task = new Todo(taskName);
            } else if (type.equals("D")) {
                task = new Deadline(taskName, parts[3]);
            } else if (type.equals("E")) {
                task = new Event(taskName, parts[3], parts[4]);
            } else {
                return null;
            }

            if (isDone) {
                task.mark();
            }
            return task;
        } catch (Exception e) {
            System.out.println("Skipping corrupted task entry: " + s);
            return null;
        }
    }
}
