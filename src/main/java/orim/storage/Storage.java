package orim.storage;

import orim.task.Task;
import orim.task.Todo;
import orim.task.Deadline;
import orim.task.Event;
import orim.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private final File file;

    public Storage(String pathname) {
        this.file = new File(pathname);
    }

    /**
     * Loads tasks from device.
     *
     * @return a history of users' tasks.
     * @throws FileNotFoundException when the file is not found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        Scanner scf = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (scf.hasNext()) {
            String[] tempStrArr = scf.nextLine().split(",");
            switch (tempStrArr[0]) {
            case "T":
                tasks.add(new Todo(tempStrArr[1], tempStrArr[2]));
                break;
            case "D":
                tasks.add(new Deadline(tempStrArr[1], tempStrArr[2], tempStrArr[3]));
                break;
            case "E":
                tasks.add(new Event(tempStrArr[1], tempStrArr[2], tempStrArr[3], tempStrArr[4]));
                break;
            }
        }

        return tasks;
    }

    /**
     * Stores the history when exit the bot.
     *
     * @param tasks the current TaskList.
     * @throws IOException when file cannot be written by FileWriter.
     */
    public void store(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(file);
        String tempLog = "";
        for (int i = 1; i < tasks.size() + 1; i++) {
            tempLog = tempLog + tasks.get(i - 1).generateLog() + "\n";
        }
        fw.write(tempLog);
        fw.close();
    }

}


















