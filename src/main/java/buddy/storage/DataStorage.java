package buddy.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import buddy.command.Command;
import buddy.exception.BuddyDataStorageException;
import buddy.exception.BuddyException;
import buddy.task.Deadline;
import buddy.task.Event;
import buddy.task.Task;
import buddy.task.TaskList;
import buddy.task.Todo;

/**
 * Represents data storage on hard disk
 */
public class DataStorage {
    private String filePath;

    /**
     * Constructor for Data Storage
     *
     * @param filePath File path on hard disk
     */
    public DataStorage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data array list.
     *
     * @return the array list
     * @throws BuddyException the buddy exception
     */
    public ArrayList<Task> loadData() throws BuddyException {
        File file = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                taskList.add(this.processEntry(line));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            this.createDataStorage();
        }
        return taskList;
    }

    private Task processEntry(String line) throws BuddyException {
        try {
            String[] data = line.split(" \\| ");
            Task task = null;
            return switch (data[0]) {
                case "T" -> {
                    task = new Todo(data[2]);
                    yield setTaskDone(Integer.parseInt(data[1]), task);
                }
                case "D" -> {
                    task = new Deadline(data[2], Command.getDateAndTime(data[3]));
                    yield setTaskDone(Integer.parseInt(data[1]), task);
                }
                case "E" -> {
                    task = new Event(data[2], Command.getDateAndTime(data[3]), Command.getDateAndTime(data[4]));
                    yield setTaskDone(Integer.parseInt(data[1]), task);
                }
                default -> throw new BuddyDataStorageException("Invalid entry in data");
            };
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BuddyDataStorageException("The data file has been corrupted");
        }

    }

    private Task setTaskDone(int statusField, Task task) {
        if (statusField == 1) {
            task.markTaskAsDone();
        }
        return task;
    }

    private void createDataStorage() throws BuddyException {
        File file = new File(this.filePath);
        File folder = new File(file.getParent());
        folder.mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new BuddyDataStorageException("Cannot create data storage at the moment");
        }
    }

    /**
     * Saves tasks to storage.
     *
     * @param taskList the task list
     * @throws BuddyException the buddy exception
     */
    public void saveTasksToStorage(TaskList taskList) throws BuddyException {
        writeToFile("", false);
        for (int i = 0; i < taskList.getLength(); i += 1) {
            writeToFile(taskList.getTask(i).toStorageStringFormat(), true);
        }
    }

    /**
     * Writes to file.
     *
     * @param entryLine  the entry line
     * @param isAppended the is appended
     * @throws BuddyException the buddy exception
     */
    public void writeToFile(String entryLine, boolean isAppended) throws BuddyException {
        try {
            FileWriter fw = new FileWriter(this.filePath, isAppended);
            fw.write(entryLine);
            fw.close();
        } catch (IOException e) {
            throw new BuddyDataStorageException("Cannot write tasks to file");
        }
    }

}
