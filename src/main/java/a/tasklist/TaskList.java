package a.tasklist;
import java.util.ArrayList;

import a.task.*;


/**
 * Manages a list of tasks, including adding, removing, retrieving, and displaying tasks.
 */
public class TaskList {
    private ArrayList<Task> list;
    /**
     * Constructs a TaskList instance with an existing list of tasks.
     *
     * @param list The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return list.size();
    }
    /**
     * Retrieves a task at the specified index.
     *
     * @param index The index of the task to retrieve (0-based).
     * @return The task at the given index.
     */
    public Task get(int index) {
        return list.get(index);
    }
    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     */

    public void add(Task task) {
        list.add(task);
    }
    /**
     * Removes and returns the task at the specified index.
     *
     * @param index The index of the task to remove (0-based).
     * @return The removed task.
     */
    public Task remove(int index) {
        return list.remove(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return An ArrayList containing all tasks.
     */

    public ArrayList<Task> getTasks() {
        return list;
    }
    /**
     * Displays all tasks in the list with their respective indices.
     */
    public void showTasks() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }
}
