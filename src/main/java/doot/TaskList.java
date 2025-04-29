package doot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private List<Task> arr;
    public TaskList() {
        arr = new ArrayList<>();
    }

    //creates a new task from the userinput str, adds it to the list, and creates a message
    public String addTask(String str) throws InvalidFormatException {
        Task task = Task.makeTask(str);
        arr.add(task);
        Ui.showMessage("Task added!\n   " + task.getDetails() + "\nEwe now have " + arr.size() + " tasks in the list!");
        return "Task added!\n   " + task.getDetails() + "\nEwe now have " + arr.size() + " tasks in the list!";
    }

    //
    //@param

    /**
     *  loads tasks by running the strings needed from the file.
     * @param f File f is the file which contains the past commands used to make the list
     * @throws FileNotFoundException throw when file is not found
     * @throws InvalidFormatException
     */
    public void loadTask(File f) throws FileNotFoundException, InvalidFormatException {
        Scanner s = new Scanner(f);
        int count = 0;
        try {
            while (s.hasNext()) {
                String line = s.nextLine();
                if (line.startsWith("d ")) {
                    line = line.substring(2);
                    Task task = Task.makeTask(line);
                    arr.add(task);
                    this.mark(count);
                } else {
                    Task task = Task.makeTask(line);
                    arr.add(task);
                }
                count += 1;
            }
        } catch (InvalidFormatException e) {
            throw new InvalidFormatException("File corrupted. Check if anything is salvageable, or it will be overwritten");
        }
    }

    /**
     * returns the entire list, in the format used for printing as a list when called by the user
     * @return the list with numbered tasks
     */
    public String returnList() {
        if (arr.isEmpty()) {
            return "Tasks all done! You are the Strongest!";
        }
        int count = 1;
        StringBuilder list = new StringBuilder();
        for (Task task : arr) {
            list.append(count).append(". ").append(task.getDetails()).append("\n");
            count ++;
        }
        return list.toString().trim();
    }

    /**
     * used for saving. This assembles the creationString() of all tasks and seperates them in their own lines
     * @return the strings needed to recreate the list
     */
    public String listData() {
        StringBuilder list = new StringBuilder();
        for (Task task : arr) {
            list.append(task.creationString()).append("\n");
        }
        return list.toString().trim();
    }

    /**
     * //gets the size of the list
     * @return int representing size
     */
    public int size() {
        return arr.size();
    }

    /**
     * marks the task in the list
     * @param num the index of the task be marked
     */
    public void mark(int num) {
        arr.get(num).setDone();
    }

    /**
     * unmarks the task in the list
     * @param num the index of task to be unmarked
     */
    public void unMark(int num) {
        arr.get(num).setUndone();
    }

    /**
     * gets the task
     * @param num the index of the task to be gotten
     * @return the task
     */
    public Task getTask(int num) {
        return arr.get(num);
    }

    /**
     * deletes tasks
     * @param num the index of the task to be deleted
     */
    public void removeTask(int num) {
        arr.remove(num);
    }

    /**
     * finds the list of matching tasks
     * @param str the substring used to find all tasks that contains the substring
     * @return the list of tasks
     */
    public String searchWord(String str) {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            if (this.getTask(i).isSubstring(str)) {
                list.append(this.getTask(i).getDetails()).append("\n");
            }
        }
        return list.toString().strip();
    }
}
