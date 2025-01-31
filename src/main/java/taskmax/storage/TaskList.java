package taskmax.storage;

import taskmax.exception.TaskmaxException;
import taskmax.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
<<<<<<< HEAD
 * Represents a list of tasks and provides methods to manage them.
=======
 * Represents a list of tasks and provides methods to manipulate them.
>>>>>>> branch-A-CodingStandard
 */
public class TaskList {
    private final List<Task> tasks;

    /**
<<<<<<< HEAD
     * Constructs an empty task list.
=======
     * Constructs an empty TaskList.
>>>>>>> branch-A-CodingStandard
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
<<<<<<< HEAD
     * Constructs a task list with a given list of tasks.
     *
     * @param tasks The list of tasks to initialise the task list with.
=======
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize with.
>>>>>>> branch-A-CodingStandard
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
<<<<<<< HEAD
     * Adds a task to the task list.
=======
     * Adds a new task to the list.
>>>>>>> branch-A-CodingStandard
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
<<<<<<< HEAD
     * Removes a task from the task list at the specified index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     * @throws TaskmaxException If the index is invalid.
=======
     * Removes a task at the specified index.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     * @throws TaskmaxException If the index is out of bounds.
>>>>>>> branch-A-CodingStandard
     */
    public Task removeTask(int index) throws TaskmaxException {
        validateIndex(index);
        return tasks.remove(index);
    }

    /**
<<<<<<< HEAD
     * Marks a task as done or not done at the specified index.
     *
     * @param index  The index of the task to be marked.
     * @param isDone {@code true} to mark as done, {@code false} to mark as not done.
     * @throws TaskmaxException If the index is invalid.
=======
     * Marks or unmarks a task at the specified index.
     *
     * @param index  The index of the task to mark/unmark.
     * @param isDone True to mark as done, false to mark as not done.
     * @throws TaskmaxException If the index is out of bounds.
>>>>>>> branch-A-CodingStandard
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
<<<<<<< HEAD
     * Returns the list of tasks.
=======
     * Retrieves the list of tasks.
>>>>>>> branch-A-CodingStandard
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
<<<<<<< HEAD
     * @return The task at the specified index.
     * @throws TaskmaxException If the index is invalid.
=======
     * @return The requested task.
     * @throws TaskmaxException If the index is out of bounds.
>>>>>>> branch-A-CodingStandard
     */
    public Task get(int index) throws TaskmaxException {
        validateIndex(index);
        return tasks.get(index);
    }

    /**
<<<<<<< HEAD
     * Returns the number of tasks in the task list.
=======
     * Returns the number of tasks in the list.
>>>>>>> branch-A-CodingStandard
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
<<<<<<< HEAD
     * @return {@code true} if the task list is empty, {@code false} otherwise.
=======
     * @return True if the list is empty, false otherwise.
>>>>>>> branch-A-CodingStandard
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
