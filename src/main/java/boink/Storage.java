package boink;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import boink.tasks.Deadline;
import boink.tasks.Event;
import boink.tasks.Task;
import boink.tasks.ToDoTask;
import boink.utils.Utils;

/**
 * This class deals with loading tasks from the file and saving tasks in the file.
 */

public class Storage {

    private String filePath;
    private String archiveFilePath;

    /**
     * Constructor for storage.
     * @param path
     */

    public Storage(String path) {
        String workingDirectory = System.getProperty("user.dir");
        try {
            Path directoryPath = Paths.get(workingDirectory + File.separator + path);
            Files.createDirectories(directoryPath);
            this.filePath = workingDirectory + File.separator + path + "/data.txt";
            this.archiveFilePath = workingDirectory + File.separator + path + "/archivedData.txt";
        } catch (IOException err) {
            System.out.println("Error occurred initializing Storage. " + err.getMessage());
        }
    }

    /**
     * Loads tasks from file into taskList.
     * @param tasklist Tasklist to load tasks from file into.
     * @throws FileNotFoundException If file does not exist.
     */

    public void loadTasksFromFile(TaskList tasklist) throws FileNotFoundException {
        File tasksFile = new File(this.filePath);
        Scanner scanner = new Scanner(tasksFile);
        assert tasklist != null : "Tasklist should not be null";

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Task task = this.parseTaskLog(line);
            tasklist.loadTask(task);
        }
    }


    /**
     * Writes tasks from taskList into file
     * IOException thrown and caught if error occurred.
     * @param taskList Tasklist to read tasks for writing.
     */

    public void saveTasksToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write(taskList.saveTasks());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks to file " + e.getMessage());
        }
    }

    /**
     * Writes tasks from taskList into archive file
     * IOException thrown and caught if error occurred.
     * @param taskList Tasklist to read tasks for archiving.
     */

    public void archiveTasksToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(archiveFilePath, true);
            fw.write(taskList.saveTasks());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error occurred while archiving tasks to archive file "
                    + e.getMessage());
        }
    }

    /**
     * Parse file entry into tasks for loading to tasklist.
     * @param input Entry to be parsed.
     * @return Task to be loaded.
     */

    private Task parseTaskLog(String input) {
        String[] parts = input.split("\\|");
        boolean isDone = parts[1].trim().equals("1");
        Task task = null;
        switch (parts[0].trim()) {
        case "T":
            task = new ToDoTask(parts[2].trim());
            break;
        case "D":
            task = new Deadline(parts[2].trim(), Utils.createDateTime(parts[3].trim()));
            break;
        case "E":
            task = new Event(parts[2].trim(), Utils.createDateTime(parts[3].trim()),
                    Utils.createDateTime(parts[4].trim()));
            break;
        default:
            return task;
        }

        if (isDone) {
            task.setDone();
        }

        return task;
    }
}
