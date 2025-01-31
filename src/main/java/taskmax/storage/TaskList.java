package taskmax.storage;

import taskmax.exception.TaskmaxException;
import taskmax.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and provides methods to manipulate them.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task at the specified index.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     * @throws TaskmaxException If the index is out of bounds.
     */
    public Task removeTask(int index) throws TaskmaxException {
        validateIndex(index);
        return tasks.remove(index);
    }

    /**
     * Marks or unmarks a task at the specified index.
     *
     * @param index  The index of the task to mark/unmark.
     * @param isDone True to mark as done, false to mark as not done.
     * @throws TaskmaxException If the index is out of bounds.
     */
    public void markTask(int index, boolean isDone) throws TaskmaxException {
        validateIndex(index);
        if (isDone) {
            tasks.get(index).markAsDone();
        } else {
            tasks.get(index).markAsNotDone();
        }
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The requested task.
     * @throws TaskmaxException If the index is out of bounds.
     */
    public Task get(int index) throws TaskmaxException {
        validateIndex(index);
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
     * Checks if the task list is empty.
     *
     * @return True if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Validates whether the given index is within the valid range.
     *
     * @param index The index to validate.
     * @throws TaskmaxException If the index is out of bounds.
     */
    private void validateIndex(int index) throws TaskmaxException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskmaxException("Invalid task number! Please enter a valid task index.");
        }
    }
}
