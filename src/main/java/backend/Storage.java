package backend;

import backend.task.EventTask;
import backend.task.ToDoTask;
import backend.task.DeadlineTask;
import backend.task.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    String filePath;

    /**
     * Creates a new storage class
     *
     * @param filePath the path of the file to be written to
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    // @@author testing1234567891011121314
    // Reused from https://github.com/testing1234567891011121314/ip
    // with minor modifications
    /**
     * Reads File and returns contents as an arraylist of Strings.
     * @return an arraylist of Tasks
     */
    public ArrayList<Task> readDataFromDisk() {
        ArrayList<Task> contents = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                contents.add(readEntry(s.nextLine()));
            }
            s.close();
        } catch (FileNotFoundException e) {
            //throw new ReminderebotException("File not found!");
        }
        return contents;
    }

    /**
     * Read the string entry and return a task
     * @param entry
     * @return Task corresponding to string
     */
    private Task readEntry(String entry) {
        String[] fields = entry.split("\\|");
        Task taskToAdd;
        // @@author david-eom
        // Reused from https://github.com/david-eom/CS2103T-IP
        // with minor modifications
        switch (fields[0]) {
            case "E":
                taskToAdd = new EventTask(fields[2], fields[3], fields[4]);
                break;
            case "D":
                taskToAdd = new DeadlineTask(fields[2], fields[3]);
                break;
            case "T":
                taskToAdd = new ToDoTask(fields[2]);
                break;
            default:
                throw new RuntimeException("This should not happen");
        }
        if ((fields[1]).equals("X")) {
            taskToAdd.markAsDone();
        }
        return taskToAdd;
    }

    /**
     * Writes all tasks from ToDoList into the filePath that Storage was initialised with.
     * If a directory existed before, no directory will be created.
     * If the file existed before, it's contents will be overwritten with the new content of the ToDoList.
     * Otherwise, a new parent directory will be created for the file
     * and the file will be created andwritten to.
     *
     * @param taskList the ToDoList containing the tasks to be written to the file
     */
    // @@author testing1234567891011121314
    // Reused from https://github.com/testing1234567891011121314/ip
    // with minor modifications
    public void saveDataToDisk(ToDoList taskList) {
        try {
            File file = new File(filePath);
            File dir = new File(file.getParent());
            boolean dirCreated = dir.mkdirs();
            FileWriter fw = new FileWriter(filePath, false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < taskList.getLength(); i++) {
                bw.write(taskList.getTask(i).toFileEntry());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException("This should never happen", e);
        }
    }
}
