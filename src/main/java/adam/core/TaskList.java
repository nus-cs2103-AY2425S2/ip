package adam.core;

import java.time.LocalDate;
import java.util.ArrayList;

import adam.tasks.Task;

/**
 * Represents the list of tasks.
 */
public class TaskList {
    /** List of tasks */
    private ArrayList<Task> tasks;

    /** An instance of Storage to handle saving task list to file */
    private Storage storage;

    /**
     * Constructor for TaskList.
     *
     * @param store An instance of Storage to handle saving task list to file.
     */
    public TaskList(Storage store) {
        this.storage = store;
        this.tasks = this.storage.loadLog();
    }

    /**
     * Lists all tasks in the task list.
     *
     * @return ArrayList of strings representing tasks
     */
    public ArrayList<String> listAll() {
        ArrayList<String> outputs = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            outputs.add(String.format("%d. %s", i + 1, this.tasks.get(i)));
        }

        return outputs;
    }

    /**
     * Lists all tasks on a given date.
     *
     * @param date The date to filter tasks by.
     * @return ArrayList of strings representing tasks that occur on the date
     */
    public ArrayList<String> listAllOnDate(LocalDate date) {
        ArrayList<String> outputs = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task.isOn(date)) {
                outputs.add(String.format("%d. %s", i + 1, task));
            }
        }

        return outputs;
    }

    /**
     * Lists all tasks that match a query.
     *
     * @param query The query to match tasks against.
     * @return ArrayList of strings representing tasks that match the query.
     */
    public ArrayList<String> listMatches(String query) {
        ArrayList<String> outputs = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task.containsQuery(query)) {
                outputs.add(String.format("%d. %s", i + 1, task));
            }
        }

        return outputs;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.storage.saveLog(this.tasks);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        // may throw IndexOutOfBoundsException
        Task deletedTask = this.tasks.remove(index);

        this.storage.saveLog(this.tasks);
        return deletedTask;
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark as done.
     * @return A String representing the task that was marked.
     */
    public String markDone(int index) {
        // may throw IndexOutOfBoundsException
        Task task = this.tasks.get(index);
        task.markDone();

        this.storage.saveLog(this.tasks);
        return task.toString();
    }

    /**
     * Unmarks a task as done.
     *
     * @param index The index of the task to unmark as done.
     * @return a String representing the task that was unmarked
     */
    public String unmarkDone(int index) {
        // may throw IndexOutOfBoundsException
        Task task = this.tasks.get(index);
        task.unmarkDone();

        this.storage.saveLog(this.tasks);
        return task.toString();
    }
}
