package nikingoda.Storage;

import nikingoda.NikingodaException.NikingodaException;
import nikingoda.Parser.Parser;
import nikingoda.Task.Task;
import nikingoda.TaskList.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private final String folderPath;

    public Storage(String folderPath, String filePath) throws NikingodaException {
        this.folderPath = folderPath;
        this.filePath = filePath;
        File dir = new File(this.folderPath);
        if(!dir.exists()) {
            dir.mkdir();
        }
        File taskFile = new File(dir, filePath);
        try {
            if(!taskFile.exists()) {
                taskFile.createNewFile();
            }
        } catch (Exception e) {
            throw new NikingodaException("Cannot create file to save tasks");
        }
    }

    public ArrayList<Task> loadTasks() throws NikingodaException {
        ArrayList<Task> tasks = new ArrayList<>();
        File dir = new File(this.folderPath);
        if(!dir.exists()) {
            dir.mkdir();
        }
        File taskFile = new File(dir, filePath);
        if (!taskFile.exists()) {
            return tasks;
        }
        try (Scanner scanTask = new Scanner(taskFile)) {
            while (scanTask.hasNextLine()) {
                String nextLine = scanTask.nextLine();
                tasks.add(Parser.parseTask(nextLine));
            }
        } catch (Exception e) {
            throw new NikingodaException(e.getMessage());
        }
        return tasks;
    }

    public void saveTask(TaskList tasks) throws NikingodaException {
        try {
            File dir = new File(this.folderPath);
            if(!dir.exists()) {
                dir.mkdir();
            }
            File taskFile = new File(dir, this.filePath);
            if(!taskFile.exists()) {
                taskFile.createNewFile();
            }
            FileWriter writer = new FileWriter(taskFile);
            for (Task task : tasks.getTasks()) {
                writer.write(task.toFile() + System.lineSeparator());
            }
            writer.close();
        } catch (Exception e) {
            throw new NikingodaException("Cannot save task");
        }
    }
}