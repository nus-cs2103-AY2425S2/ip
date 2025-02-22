package marshmallow.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import marshmallow.MarshmallowException;
import marshmallow.storage.Storage;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;
    private Storage<Task> storage;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with existing tasks.
     *
     * @param tasks The existing tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a task list, backed by a storage driver.
     *
     * @param storage The storage driver.
     */
    public TaskList(Storage<Task> storage) {
        this.storage = storage;
        this.tasks = storage.load();
    }

    /**
     * Saves the task list to storage.
     *
     * If no storage driver is provided, this method does nothing.
     */
    public void save() {
        if (storage == null) {
            return;
        }

        storage.save(tasks);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
        save();
    }

    /**
     * Removes a task from the task list.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task removeTask(int index) {
        Task removedTask;

        try {
            removedTask = tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new MarshmallowException("I can't find that task! Which one are you referring to?");
        }

        save();
        return removedTask;
    }

    /**
     * Retrieves a task from the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task.
     */
    public Task getTask(int index) {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new MarshmallowException("I can't find that task! Which one are you referring to?");
        }
    }

    /**
     * Filters the tasks in the task list by a query.
     *
     * @param query The query to filter by.
     * @return The filtered tasks.
     */
    public List<Task> filterTasks(String query) {
        return tasks.parallelStream().filter(task ->
                task.getDescription().toLowerCase().contains(query.toLowerCase())).toList();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks.
     */
    public int count() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ListIterator<Task> it = tasks.listIterator(); it.hasNext(); ) {
            sb.append(it.nextIndex() + 1).append(".").append(it.next());

            if (it.hasNext()) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}
