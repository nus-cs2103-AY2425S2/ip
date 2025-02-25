package SirDuke.backend;

import SirDuke.backend.task.DeadlineTask;
import SirDuke.backend.task.Task;
import SirDuke.backend.task.ToDoTask;
import SirDuke.backend.task.EventTask;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Responsible for the storage of the tasks to and from disk in the chatbot.
 */
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
     *
     * @return an arraylist of Tasks
     * @throws FileNotFoundException if file is not found
     */
    public ArrayList<Task> readDataFromDisk() throws FileNotFoundException {
        ArrayList<Task> contents = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            contents.add(readEntry(s.nextLine()));
        }
        s.close();
        return contents;
    }

    /**
     * Read the string entry and return a task
     * @param entry the entry representing a task object
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
     * and the file will be created and written to.
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
