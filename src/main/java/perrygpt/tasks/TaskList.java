package perrygpt.tasks;

import perrygpt.exceptions.PerryGPTException;

import java.util.ArrayList;

/**
 * The {@code TaskList} class represents a list of tasks.
 * It provides operations to manage the tasks, such as adding, removing, updating, and retrieving tasks.
 */
public class TaskList {
    /**
     * The list of tasks being managed.
     */
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     * Initializes an empty {@link ArrayList} to store tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} initialized with a given list of tasks.
     *
     * @param tasks The list of tasks to initialize the {@code TaskList}.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The {@link Task} to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index The index of the task to be removed (zero-based).
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 or index >= size()).
     */
    public void removeTask(int index) throws IndexOutOfBoundsException {
        tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve (zero-based).
     * @return The {@link Task} at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 or index >= size()).
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Returns all tasks in the task list.
     *
     * @return An {@link ArrayList} containing all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Finds and returns a list of tasks that match the given keyword.
     * A task is considered a match if its string representation contains the keyword (case insensitive).
     *
     * @param keyword The search keyword to filter tasks.
     * @return An {@link ArrayList} of tasks that contain the keyword.
     * @throws IllegalArgumentException If the keyword is null.
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null : "Keyword should not be null";
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

    /**
     * Updates a task at the specified index with a new task.
     *
     * @param index       The index of the task to update (zero-based).
     * @param updatedTask The new {@link Task} to replace the existing one.
     * @throws PerryGPTException If the index is out of range (index < 0 or index >= size()).
     */
    public void updateTask(int index, Task updatedTask) throws PerryGPTException {
        if (index < 0 || index >= tasks.size()) {
            throw new PerryGPTException("Invalid task index.");
        }
        tasks.set(index, updatedTask);
    }
}