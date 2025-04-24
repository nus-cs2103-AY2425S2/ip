package jimmy.tasks;

import java.util.ArrayList;

/**
 * Manages a list of tasks, providing methods to add, retrieve, and delete tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list with existing tasks.
     *
     * @param tasks the list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index the index of the task to retrieve.
     * @return the task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes a task by its index.
     *
     * @param index the index of the task to delete.
     * @return the deleted task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves all tasks in the list.
     *
     * @return the list of all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}
