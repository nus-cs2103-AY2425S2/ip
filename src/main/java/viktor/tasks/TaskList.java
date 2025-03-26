package viktor.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks, allowing for adding, removing, and retrieving tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a TaskList with an empty list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list by its index.
     *
     * @param index The index of the task to remove.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the list has no tasks, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Sets the list of tasks.
     *
     * @param tasks The list of tasks to set.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Clears the task list, removing all tasks.
     */
    public void clear() {
        tasks.clear();
    }
}
