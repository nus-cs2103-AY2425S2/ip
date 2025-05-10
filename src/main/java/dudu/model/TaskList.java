package dudu.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import dudu.controller.FileOperation;
import dudu.utils.DuduException;

public class TaskList {
    /** Current list of tasks **/
    private ArrayList<Task> tasks;

    /** Creates TaskList object. **/
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a new entry to the current list of tasks.
     *
     * @param task Current entry to be added.
     * @return String containing entry added and the current number of tasks in the list.
     */

    public String addEntry(Task task) throws DuduException, IOException {
        this.tasks.add(task);
        return "Alright! I've added this task:\n" + task.toString() + printNumberOfTasks();
    }

    /**
     * Returns all the tasks in the list.
     *
     * @return The tasks in the list.
     */

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the current number of tasks in the list.
     *
     * @return Current number of tasks.
     */
    public String printNumberOfTasks() {
        String isPlural = this.tasks.size() != 1
                          ? " tasks in the list."
                          : " task in the list.";
        return "\nNow you have " + this.tasks.size() + isPlural;
    }

    /**
     * Deletes task with indicated index.
     *
     * @param index Number of task to be deleted.
     * @return String containing task to be deleted and the current number of tasks in the list.
     */
    public String deleteTask(int index) {
        assert index >= 0;
        Task curr = this.tasks.get(index);
        this.tasks.remove(index);
        return "Alright! I've deleted this task:\n" + curr.toString() + printNumberOfTasks();
    }
}
