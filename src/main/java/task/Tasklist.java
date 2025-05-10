package task;

import java.util.ArrayList;

import exceptions.ElmachoException;

/**
 * This class contains methods for managing tasks in a Tasklist.
 */
public class Tasklist {

    private final ArrayList<Task> tasks;

    public Tasklist() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Adds a task to the Tasklist.
     * @param task the Task object to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the Tasklist.
     * @param n The index of the task to be deleted.
     * @throws ElmachoException if the index is not on the tasklist.
     */
    public void delete(int n) throws ElmachoException {
        if (n > 0 && n <= tasks.size()) {
            tasks.remove(n - 1);
        } else {
            throw new ElmachoException("Invalid task number.");
        }
    }

    /**
     * Puts an "X" in the checkbox of the task.
     * @param n The index of the task to be marked.
     */
    public void mark(int n) {
        if (n >= 0 && n < tasks.size()) {
            tasks.get(n).mark();
        }
    }

    /**
     * Removes any "X" from the checkbox.
     * @param n The index of the task to be unmarked.
     */
    public void unmark(int n) {
        if (n >= 0 && n < tasks.size()) {
            tasks.get(n).unmark();
        }
    }

}
