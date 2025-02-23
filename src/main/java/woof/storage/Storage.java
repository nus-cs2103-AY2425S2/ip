package woof.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import woof.task.Deadline;
import woof.task.Event;
import woof.task.Task;
import woof.task.TaskList;
import woof.task.Todo;

/**
 * Represents the storage of tasks locally. It is responsible for loading and saving tasks to a local file.
 */
public class Storage {
    /**
     * Loads tasks stored locally via a file path. If there is no such directory or file, this method will
     * automatically create one for users.
     *
     * @param filePath The file path to access.
     * @return A list of all the tasks stored.
     */
    public List<Task> loadTasks(String filePath) {
        List<Task> tasks = new ArrayList<>(100);
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                return tasks;
            }

            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                tasks.add(Storage.parseTask(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("WERWER! It seems that you don't have the required file!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    /**
     * Takes a line in the locally stored file and parse it into an instance of a task.
     *
     * @param line The input from a locally stored file.
     * @return A corresponding instance of a task.
     * @throws Exception Possible corruption of the file leading to unrecognisable format.
     */
    private static Task parseTask(String line) throws Exception {
        String[] split = line.split(" \\| ");
        String command = split[0];
        Task newTask = null;
        boolean isCompleted = split[1].equals("1");
        switch (command) {
        case "T":
            newTask = new Todo(split[2]);
            if (isCompleted) {
                newTask.markAsDone();
            }
            break;
        case "D":
            newTask = new Deadline(split[2], split[3]);
            if (isCompleted) {
                newTask.markAsDone();
            }
            break;
        case "E":
            newTask = new Event(split[2], split[3], split[4]);
            if (isCompleted) {
                newTask.markAsDone();
            }
            break;
        default:
            System.out.println("WERWER! I don't recognise this task: " + command);
        }
        return newTask;
    }

    /**
     * After every command, it rewrites the local file to sync with the current state.
     *
     * @param filePath The local file to access.
     */
    public static void saveTasks(String filePath) {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (int i = 1; i <= TaskList.size(); i++) {
                fw.write(TaskList.getTask(i).print() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("WERWER! Failed to save: " + e.getMessage());
        }
    }
}
